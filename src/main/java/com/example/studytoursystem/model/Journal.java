package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class Journal {
    private Integer journalId;
    private String title;
    private String content;
    private Integer locationId;
    private Integer popularity;
    private Integer evaluation;
    private Integer userId;
}
