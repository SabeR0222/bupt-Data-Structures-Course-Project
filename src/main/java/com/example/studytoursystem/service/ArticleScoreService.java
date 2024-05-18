package com.example.studytoursystem.service;

import com.example.studytoursystem.model.ArticleScore;
import com.example.studytoursystem.model.User;

import java.util.List;

public interface ArticleScoreService {
    //获取文章的平均分
    Double getAverageScore(Integer articleId);

    //更新用户对文章的评分并且更新文章的评分
    void setScore(Integer userId, Integer articleId, Integer score);
}
