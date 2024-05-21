package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.User;
import com.example.studytoursystem.model.ArticleScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleScoreMapper {
    @Select("select * from article_scores where article_id=#{articleId}")
    ArticleScore findByUserId(Integer articleId);

    @Select("select * from article_scores")
    List<ArticleScore> getAllScores();

    @Update("update article_scores set score = #{score} where article_id = #{articleId} and user_id = #{userId}")
    void update(int userId, int articleId, int score);

    @Insert("insert into article_scores(user_id, article_id, score) values(#{userId}, #{articleId}, #{score})")
    void insert(int userId, int articleId, int score);
}
