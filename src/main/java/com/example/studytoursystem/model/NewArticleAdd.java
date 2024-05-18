package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class NewArticleAdd {
    private Integer userId;
    private String title;
    private String content;
    private Integer locationId;
}
