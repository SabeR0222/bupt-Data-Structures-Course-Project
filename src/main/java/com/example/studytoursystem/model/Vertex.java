package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Vertex {
    private int vertexId;
    private int x;
    private int y;
    private String label;
}