package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class SimplifiedArticle {
    private int articleId;
    private String username;
    private String title;
    private String locationName;
    private Integer popularity;
    private Double evaluation;

    public SimplifiedArticle(int articleId, String username, String title, String locationName, Integer popularity, Double evaluation) {
        this.articleId = articleId;
        this.username = username;
        this.title = title;
        this.locationName = locationName;
        this.popularity = popularity;
        this.evaluation = evaluation;
    }
}


