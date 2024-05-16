package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.ArticleScore;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.service.ArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleScore")
public class ArticleScoreController {
    @Autowired
    private ArticleScoreService articleScoreService;

    @PostMapping ("/setScore")
    public Result setScore(@RequestBody ArticleScore articleScore) {
        articleScoreService.setScore(articleScore.getUserId(), articleScore.getArticleId(), articleScore.getScore());
        return Result.success();
    }

    @GetMapping("/getAverageScore/{articleId}")
    public Result<Double> getAverageScore(@PathVariable Integer articleId) {
        return Result.success(articleScoreService.getAverageScore(articleId));
    }

}
