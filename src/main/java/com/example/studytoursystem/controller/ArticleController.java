package com.example.studytoursystem.controller;

import com.example.studytoursystem.mapper.ArticleScoreMapper;
import com.example.studytoursystem.model.Article;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.model.SimplifiedArticle;
import com.example.studytoursystem.service.ArticleScoreService;
import com.example.studytoursystem.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;


    //add
    @PostMapping("/add")
    public void add(int userId, String title, String content, int locationId)
    {
        articleService.add(userId, title, content, locationId);
    }

    //+1
    @PostMapping("/updatePopularity")
    public void updatePopularity(int articleId)
    {
        articleService.updatePopularity(articleId);
    }

    //get
    @GetMapping("/getArticle")
    public Result<Article> getArticle(int articleId)
    {
        Article article = articleService.getArticle(articleId);
        return Result.success(article);
    }

    //content

    //get article by title
    @GetMapping("/getArticleByTitle")
    public Result<SimplifiedArticle> getArticleByTitle(String title)
    {
        SimplifiedArticle simplifiedArticle = articleService.getArticleByTitle(title);
        if (simplifiedArticle == null)
            return Result.error("no such article");
        return Result.success(simplifiedArticle);
    }

    //delete

    //put

    //search
}
