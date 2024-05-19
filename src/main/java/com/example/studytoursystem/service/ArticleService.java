package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Article;
import com.example.studytoursystem.model.SimplifiedArticle;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean add(Integer userId, String title, String content, int locationId);
    boolean delete(int articleId, int userId);
    boolean update(int articleId, int userId, String title, String content, int locationId);
    void updatePopularity(int articleId);
    Article getArticle(int articleId);
    List<SimplifiedArticle> recommendArticle(Integer userId);

    List<Map.Entry<Integer, Integer>> searchWord(String text, String word);

    String getContent(Integer articleId);

    SimplifiedArticle getArticleByTitle(String title);

    List<SimplifiedArticle> getArticleByLocation(Integer locationId);
}
