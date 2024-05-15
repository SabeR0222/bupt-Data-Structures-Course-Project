package com.example.studytoursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Edge {
    private int edgeId;
    private int srcId;
    private int destId;
    private int weight;
    private float crowding;
    private boolean isRideable;
}