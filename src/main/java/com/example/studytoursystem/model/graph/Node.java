package com.example.studytoursystem.model.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private int srcId;
    private List<Edge> edges;

    public Node(int srcId) {
        this.srcId = srcId;
        this.edges = new ArrayList<>();
    }
}