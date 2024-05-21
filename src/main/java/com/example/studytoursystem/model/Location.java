package com.example.studytoursystem.model;

import lombok.Data;

import java.util.Comparator;

@Data
public class Location{
    private Integer locationId;
    private String name;
    //0是学校，1是景点
    private Integer type;
    private String keyword;
    private Integer popularity;
    private Integer evaluation;


}
