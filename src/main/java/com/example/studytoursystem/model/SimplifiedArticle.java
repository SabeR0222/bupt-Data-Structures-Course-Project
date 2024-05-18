package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class SimplifiedArticle {
    private int articleId;
    private Integer userId;
    private String title;
    private Integer locationId;
    private Integer popularity;
    private Double evaluation;

    public SimplifiedArticle(int articleId, Integer userId, String title, Integer locationId, Integer popularity, Double evaluation) {
        this.articleId = articleId;
        this.userId = userId;
        this.title = title;
        this.locationId = locationId;
        this.popularity = popularity;
        this.evaluation = evaluation;
    }
}


