package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class SpotQuery {
    private Integer srcId;
    private Integer x;
    private Integer y;
    private Integer distance;
    private Integer type;
}
