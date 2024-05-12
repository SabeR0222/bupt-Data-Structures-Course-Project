package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Location {
    private Integer locationId;
    private String name;
    public enum Type {
        学校, 景区
    }
    private Type type;
    private String keyword;
    private Integer popularity;
    private Integer evaluation;
}
