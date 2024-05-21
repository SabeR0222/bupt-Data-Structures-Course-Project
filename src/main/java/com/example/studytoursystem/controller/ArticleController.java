package com.example.studytoursystem.controller;

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
    public Result add(@RequestBody NewArticleAdd newArticleAdd) {
        articleService.add(newArticleAdd.getUserId(), newArticleAdd.getTitle(), newArticleAdd.getContent(), newArticleAdd.getLocationId());
        return Result.success();
    }

    //update
    @PostMapping("/update")
    public Result update(@RequestBody NewArticleAdd newArticleAdd) {
        articleService.update(newArticleAdd.getArticleId(), newArticleAdd.getUserId(), newArticleAdd.getTitle(), newArticleAdd.getContent(), newArticleAdd.getLocationId());
        return Result.success();
    }

    //Browse count + 1
    @PostMapping("/updatePopularity/{articleId}")
    public Result updatePopularity(@PathVariable Integer articleId) {
        articleService.updatePopularity(articleId);
        return Result.success();
    }

    //get
    //TODO: 应该返回location的name和user的name
    @GetMapping("/getRecommendArticle/{userId}")
    public Result<List<SimplifiedArticle>> getRecommendArticle(@PathVariable Integer userId) {
        List<SimplifiedArticle> recommendArticle = articleService.recommendArticle(userId);
        System.out.println(recommendArticle);
        return Result.success(recommendArticle);
    }


    //content
    @GetMapping("/getContent/{articleId}")
    public Result<String> getContent(@PathVariable Integer articleId) {
        String content = articleService.getContent(articleId);
        return Result.success(content);
    }

    //get article by title
    //TODO: 应该返回location的name和user的name
    @GetMapping("/getArticleByTitle")
    public Result<SimplifiedArticle> getArticleByTitle(@RequestParam(required = false) String title) {
        SimplifiedArticle simplifiedArticle = articleService.getArticleByTitle(title);
        if (simplifiedArticle == null)
            return Result.error("no such article");
        System.out.println(simplifiedArticle);
        return Result.success(simplifiedArticle);
    }

    //delete
//    @PostMapping("/delete/{articleId}/{userId}")
//    public Result delete(@PathVariable int articleId, @PathVariable int userId) {
//        if (articleService.delete(articleId, userId) == false)
//            return Result.error("delete failed");
//        return Result.success();
//    }


    //search
    @PostMapping("/search")
    public Result<List<Map.Entry<Integer, Integer>>> search(@RequestBody Map<String, String> map) {
        String text = map.get("text");
        String word = map.get("word");
        List<Map.Entry<Integer, Integer>> list = articleService.searchWord(text, word);
        if (list == null)
            return Result.error("no such word");
        return Result.success(list);
    }

    //TODO: 应该返回location的name和user的name
    //TODO: 我传输的参数是一个String，根据String查找Article
    @GetMapping("/getArticleByLocationId/{locationId}")
    public Result<List<SimplifiedArticle>> getArticleByLocationId(@PathVariable Integer locationId) {
        List<SimplifiedArticle> articleList = articleService.getArticleByLocation(locationId);
        if (articleList == null)
            return Result.error("no such location");
        return Result.success(articleList);
    }

}
