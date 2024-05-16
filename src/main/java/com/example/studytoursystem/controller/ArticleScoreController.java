package com.example.studytoursystem.controller;

import com.example.studytoursystem.service.ArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleScore")
public class ArticleScoreController {
    @Autowired
    private ArticleScoreService articleScoreService;

    @PostMapping ("/setscore")
    public void setScore(int userId, int articleId, int score) {
        articleScoreService.setScore(userId, articleId, score);
    }

    @GetMapping("/getAverageScore")
    public Double getAverageScore(int articleId) {
        return articleScoreService.getAverageScore(articleId);
    }

}
