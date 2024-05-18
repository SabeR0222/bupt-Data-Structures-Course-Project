package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Article;
import com.example.studytoursystem.model.NewArticleAdd;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.model.SimplifiedArticle;
import com.example.studytoursystem.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;


    //add
    @PostMapping("/add")
    public void add(@RequestBody NewArticleAdd newArticleAdd)
    {
        articleService.add(newArticleAdd.getTitle(), newArticleAdd.getContent(), newArticleAdd.getLocationId());
    }

    //update
    @PostMapping("/update")
    public void update(@RequestBody NewArticleAdd newArticleAdd)
    {
        articleService.update(newArticleAdd.getUserId(), newArticleAdd.getTitle(), newArticleAdd.getContent(), newArticleAdd.getLocationId());
    }

    //Browse count + 1
    @PostMapping("/updatePopularity/{articleId}")
    public void updatePopularity(@PathVariable Integer articleId)
    {
        articleService.updatePopularity(articleId);
    }

    //get
    @GetMapping("/getArticle/{articleId}")
    public Result<Article> getArticle(@PathVariable Integer articleId)
    {
        Article article = articleService.getArticle(articleId);
        return Result.success(article);
    }

    //content
    @GetMapping("/getContent/{articleId}")
    public Result<String> getContent(@PathVariable Integer articleId)
    {
        String content = articleService.getContent(articleId);
        return Result.success(content);
    }

    //get article by title
    @GetMapping("/getArticleByTitle/{title}")
    public Result<SimplifiedArticle> getArticleByTitle(@PathVariable String title)
    {
        SimplifiedArticle simplifiedArticle = articleService.getArticleByTitle(title);
        if (simplifiedArticle == null)
            return Result.error("no such article");
        return Result.success(simplifiedArticle);
    }

    //delete
    @PostMapping("/delete/{articleId}/{userId}")
    public Result delete(@PathVariable int articleId, @PathVariable int userId)
    {
        if(articleService.delete(articleId, userId) == false)
            return Result.error("delete failed");
        return Result.success();
    }


    //search
    @PostMapping("/search/{word}")
    public Result<List<Map.Entry<Integer, Integer>>> search(@RequestBody String text, @PathVariable String word)
    {
        List<Map.Entry<Integer, Integer>> list = articleService.searchWord(text, word);
        if(list == null)
            return Result.error("no such word");
        return Result.success(list);
    }


}
