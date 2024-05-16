package com.example.studytoursystem.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Graph {
    private Map<Integer, List<EdgeNode>> adjList; //Integer是起点id

    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        adjList = new HashMap<>();
        for (Vertex v : vertexList) {
            adjList.put(v.getVertexId(), new ArrayList<>());
        }
        for (Edge e : edgeList) {
            EdgeNode edgeNode = new EdgeNode(e.getDestId(), e.getWeight(), e.getCrowding(), e.isRideable());
            adjList.get(e.getSrcId()).add(edgeNode);
            EdgeNode reverseEdgeNode = new EdgeNode(e.getSrcId(), e.getWeight(), e.getCrowding(), e.isRideable());
            adjList.get(e.getDestId()).add(reverseEdgeNode);
        }
    }
}


