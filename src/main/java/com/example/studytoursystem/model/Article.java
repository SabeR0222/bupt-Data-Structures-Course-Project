package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Article {
    private Integer articleId;
    private Integer userId;
    private String title;
    private String content;
    private Integer locationId;
    private Integer popularity;
    private Integer evaluation;
}
