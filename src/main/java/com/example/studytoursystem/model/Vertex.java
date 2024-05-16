package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Vertex {
    private Integer vertexId;
    private Integer x;
    private Integer y;
    private String label;
    private Integer type;
}