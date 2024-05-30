package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.PathMapper;
import com.example.studytoursystem.mapper.VertexMapper;

import com.example.studytoursystem.model.PathQuery;
import com.example.studytoursystem.model.graph.Graph;
import com.example.studytoursystem.service.PathService;
import com.example.studytoursystem.utils.SimulatedAnnealingTSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studytoursystem.utils.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PathServiceImpl implements PathService {
    @Autowired
    private PathMapper pathMapper;

    @Autowired
    private VertexMapper vertexMapper;

    @Override
    public List<Integer> getPath(PathQuery query) {
        Graph graph = new Graph(vertexMapper.getVertex(), pathMapper.getEdge());
        List<Integer> fullPath = new ArrayList<>(); // 使用List来动态存储路径节点
        if (query.getDestIdList().size() == 1) {
            int[] singlePath = (int[]) Dijkstra.dijkstra(graph, query.getSrcId(), query.getDestIdList().get(0), query.getStrategy()).get("path");
            // 确保路径包含了起点
            for (int node : singlePath) {
                fullPath.add(node);
            }
        } else {
            int[] pathList = new int[query.getDestIdList().size() + 1];
            pathList[0] = query.getSrcId();
            for (int i = 0; i < query.getDestIdList().size(); i++) {
                pathList[i + 1] = query.getDestIdList().get(i);
            }
            System.out.println("PathList: " + Arrays.toString(pathList));
            int[][] distanceMatrix = new int[query.getDestIdList().size() + 1][query.getDestIdList().size() + 1];
            for (int i = 0; i < pathList.length; i++) {
                for (int j = 0; j < pathList.length; j++) {
                    distanceMatrix[i][j] = (int) Dijkstra.dijkstra(graph, pathList[i], pathList[j], query.getStrategy()).get("distance");
                }
            }
            int[] bestPath = SimulatedAnnealingTSP.solveTSP(distanceMatrix);
            // 构建完整路径，注意处理循环回初始点的情况
            fullPath.add(pathList[bestPath[0]]); // 添加起始点
            for (int i = 0; i < bestPath.length; i++) {
                int nextNodeIndex = (i + 1) % bestPath.length; // 循环访问，确保最后一个连接回到起点
                int[] segment = (int[]) Dijkstra.dijkstra(graph, pathList[bestPath[i]], pathList[bestPath[nextNodeIndex]], query.getStrategy()).get("path");
                // 跳过起始点，因为它已经在 fullPath 中了
                for (int j = 1; j < segment.length; j++) {
                    fullPath.add(segment[j]);
                }
            }
        }
        return fullPath;
    }
}
