package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.VertexMapper;
import com.example.studytoursystem.model.Spot;
import com.example.studytoursystem.model.SpotQuery;
import com.example.studytoursystem.model.Vertex;
import com.example.studytoursystem.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.InsertSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class SpotServiceImpl implements SpotService {
    @Autowired
    private VertexMapper vertexMapper;

    @Override
    public List<Spot> getSpot(SpotQuery spotQuery) {
        List<Vertex> allVertices = vertexMapper.getVertex();
        List<Spot> spotList = new ArrayList<>();

        if (spotQuery.getType() == null) {
            for (Vertex vertex : allVertices) {
                // 类型匹配，体现线性查找
                if (vertex.getType() <= 11 && vertex.getType() >= 2) {
                    Spot spot = new Spot();
                    spot.setVertexId(vertex.getVertexId());

                    // 计算与查询点之间的距离
                    int x1 = vertex.getX();
                    int y1 = vertex.getY();
                    int x2 = spotQuery.getX();
                    int y2 = spotQuery.getY();
                    int distance = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                    spot.setDistance(distance);

                    // 将计算结果加入结果列表
                    spotList.add(spot);
                }
            }
        } else {
            // 线性查找匹配类型顶点，并计算距离
            for (Vertex vertex : allVertices) {
                // 类型匹配，体现线性查找
                if (Objects.equals(vertex.getType(), spotQuery.getType())) {
                    Spot spot = new Spot();
                    spot.setVertexId(vertex.getVertexId());

                    // 计算与查询点之间的距离
                    int x1 = vertex.getX();
                    int y1 = vertex.getY();
                    int x2 = spotQuery.getX();
                    int y2 = spotQuery.getY();
                    int distance = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                    spot.setDistance(distance);

                    // 将计算结果加入结果列表
                    spotList.add(spot);
                }
            }
        }

        // 排序
        Comparator<Spot> byDistanceComparator = Comparator.comparing(Spot::getDistance);
        InsertSort.insertSort(spotList, byDistanceComparator);
        return spotList;
    }
}
