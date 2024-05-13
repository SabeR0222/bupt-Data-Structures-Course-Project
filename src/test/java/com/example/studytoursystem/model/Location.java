package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Location {
    private Integer locationId;
    private String name;
    //0是景点，1是学校
    private Integer type;
    private String keyword;
    private Integer popularity;
    private Integer evaluation;
}
