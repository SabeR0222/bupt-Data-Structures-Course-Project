package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class LocationQuery {
    private String name;
    public enum Type {
        学校, 景区
    }
    private Location.Type type;
    private String keyword;
    private Integer sortord;
}
