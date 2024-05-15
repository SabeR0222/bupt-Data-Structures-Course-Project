package com.example.studytoursystem.model;

import lombok.Data;

import java.util.List;

@Data
public class PathQuery {
    private Integer srcId;
    private List<Integer> destIdList;
    private Integer strategy;
}
