package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Article;
import com.example.studytoursystem.model.SimplifiedArticle;

import java.util.List;

public interface ArticleService {
    void add(int userId, String title, String content, int locationId);
    void delete(int articleId);
    boolean update(int articleId,int userId, String title, String content, int locationId);
    void updatePopularity(int articleId);
    Article getArticle(int articleId);
    List<SimplifiedArticle> recommendArticle();

    String getContent(Integer articleId);

    SimplifiedArticle getArticleByTitle(String title);
}
