package com.example.studytoursystem.model;

import lombok.Data;

import java.util.Map;

@Data
public class Article {
    private Integer articleId;
    private Integer userId;
    private String title;
    private byte[] content;
    private String huffmanCodesJson;
    private Integer locationId;
    private Integer popularity;
    private Double evaluation;
}
