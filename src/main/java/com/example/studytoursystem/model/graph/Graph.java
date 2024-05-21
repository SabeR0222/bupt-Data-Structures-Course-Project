package com.example.studytoursystem.model.graph;


import com.example.studytoursystem.model.Vertex;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Graph {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public Graph(List<Vertex> vertexList, List<com.example.studytoursystem.model.Edge> edgeList) {
        this.nodes = new ArrayList<>();
        for (Vertex v : vertexList) {
            nodes.add(new Node(v.getVertexId()));
        }
        for (com.example.studytoursystem.model.Edge e : edgeList) {
            nodes.get(e.getSrcId()).getEdges().add(new Edge(e.getDestId(), e.getWeight(), e.getCrowding(), e.isRideable()));
            nodes.get(e.getDestId()).getEdges().add(new Edge(e.getSrcId(), e.getWeight(), e.getCrowding(), e.isRideable()));
        }


//        for (Edge e : edgeList) {
//            EdgeNode edgeNode = new EdgeNode(e.getDestId(), e.getWeight(), e.getCrowding(), e.isRideable());
//            adjList.get(e.getSrcId()).add(edgeNode);
//            EdgeNode reverseEdgeNode = new EdgeNode(e.getSrcId(), e.getWeight(), e.getCrowding(), e.isRideable());
//            adjList.get(e.getDestId()).add(reverseEdgeNode);
//        }
    }
}
