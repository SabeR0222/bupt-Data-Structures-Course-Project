package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.controller.ArticleScoreController;
import com.example.studytoursystem.mapper.ArticleMapper;
import com.example.studytoursystem.mapper.ArticleScoreMapper;
import com.example.studytoursystem.model.ArticleScore;
import com.example.studytoursystem.service.ArticleScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleScoreServicelmpl implements ArticleScoreService {
    @Autowired
    ArticleScoreMapper articleScoreMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Double getAverageScore(Integer articleId) {
        List<ArticleScore> articleScores = articleScoreMapper.getAllScores();
        int sum = 0, count = 0;
        for(ArticleScore articleScore : articleScores) {
            if(articleScore.getArticleId() == articleId) {
                sum += articleScore.getScore();
                count++;
            }
        }
        if(count != 0)
            return (double) sum / count;
        else
            return 0.0;
    }

    @Override
    public void setScore(Integer userId, Integer articleId, Integer score) {
        List<ArticleScore> articleScores = articleScoreMapper.getAllScores();
        for(ArticleScore articleScore : articleScores){
            if(Objects.equals(articleScore.getUserId(), userId) && Objects.equals(articleScore.getArticleId(), articleId)) {
                articleScoreMapper.update(userId, articleId, score);
                articleMapper.updateEvaluation(articleId, getAverageScore(articleId));
                return;
            }
        }
        articleScoreMapper.insert(userId, articleId, score);
        articleMapper.updateEvaluation(articleId, getAverageScore(articleId));
    }
}
