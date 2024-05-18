package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Article;
import com.example.studytoursystem.model.SimplifiedArticle;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean add(String title, String content, int locationId);
    boolean delete(int articleId, int userId);
    boolean update(int articleId, String title, String content, int locationId);
    void updatePopularity(int articleId);
    Article getArticle(int articleId);
    List<SimplifiedArticle> recommendArticle();

    List<Map.Entry<Integer, Integer>> searchWord(String text, String word);

    String getContent(Integer articleId);

    SimplifiedArticle getArticleByTitle(String title);
}
