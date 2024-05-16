package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.PathMapper;
import com.example.studytoursystem.mapper.VertexMapper;
import com.example.studytoursystem.model.Graph;
import com.example.studytoursystem.model.PathQuery;
import com.example.studytoursystem.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Dijkstra;

import java.util.ArrayList;
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
        if(query.getDestIdList().size() == 1){
            Dijkstra dijkstra = new Dijkstra(graph, query.getSrcId(), query.getDestIdList().get(0), query.getStrategy());
            int[] singlePath = dijkstra.dijkstra();
            // 确保路径包含了起点
            for (int node : singlePath) {
                fullPath.add(node);
            }
        } else {
            // 添加起始点到首个目的地的路径
            Dijkstra dijkstraFirst = new Dijkstra(graph, query.getSrcId(), query.getDestIdList().get(0), query.getStrategy());
            int[] firstPath = dijkstraFirst.dijkstra();
            for (int node : firstPath) {
                fullPath.add(node);
            }

            // 连接中间目的地的路径
            for (int i = 1; i < query.getDestIdList().size(); i++) {
                Dijkstra dijkstraIntermediate = new Dijkstra(graph, query.getDestIdList().get(i-1), query.getDestIdList().get(i), query.getStrategy());
                int[] intermediatePath = dijkstraIntermediate.dijkstra();
                // 跳过重复的起点（上一段路径的终点，本段路径的起点）
                for (int j = 1; j < intermediatePath.length; j++) {
                    fullPath.add(intermediatePath[j]);
                }
            }
        }
        return fullPath;
    }
}
