package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.VertexMapper;
import com.example.studytoursystem.model.Spot;
import com.example.studytoursystem.model.SpotQuery;
import com.example.studytoursystem.model.Vertex;
import com.example.studytoursystem.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studytoursystem.utils.InsertSort;

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

        // Check if type is specified in the query
        if (spotQuery.getType() == null) {
            for (Vertex vertex : allVertices) {
                // Type matching, linear search
                if (vertex.getType() <= 11 && vertex.getType() >= 2) {
                    int x1 = vertex.getX();
                    int y1 = vertex.getY();
                    int x2 = spotQuery.getX();
                    int y2 = spotQuery.getY();
                    int distance = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

                    // Check if distance falls within the specified range
                    if (distance <= spotQuery.getDistance()) {
                        Spot spot = new Spot();
                        spot.setVertexId(vertex.getVertexId());
                        spot.setDistance(distance);
                        spotList.add(spot);
                    }
                }
            }
        } else {
            // Linear search for vertices matching the type and calculate distance
            for (Vertex vertex : allVertices) {
                if (Objects.equals(vertex.getType(), spotQuery.getType())) {
                    int x1 = vertex.getX();
                    int y1 = vertex.getY();
                    int x2 = spotQuery.getX();
                    int y2 = spotQuery.getY();
                    int distance = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

                    // Check if distance falls within the specified range
                    if (distance <= spotQuery.getDistance()) {
                        Spot spot = new Spot();
                        spot.setVertexId(vertex.getVertexId());
                        spot.setDistance(distance);
                        spotList.add(spot);
                    }
                }
            }
        }

        // Sorting
        Comparator<Spot> byDistanceComparator = Comparator.comparing(Spot::getDistance);
        InsertSort.insertSort(spotList, byDistanceComparator);
        return spotList;
    }
}
