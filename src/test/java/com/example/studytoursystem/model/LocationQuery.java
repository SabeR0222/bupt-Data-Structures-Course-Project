package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class LocationQuery {
    private String name;
    //0是景点，1是学校
    private Integer type;
    private String keyword;
    //0是按热度排序，1是按评价排序,null是不排序
    private Integer sortOrder;

    public LocationQuery(String name, Integer type, String keyword, Integer sortOrder) {
        this.name = name;
        this.type = type;
        this.keyword = keyword;
        this.sortOrder = sortOrder;
    }
}
