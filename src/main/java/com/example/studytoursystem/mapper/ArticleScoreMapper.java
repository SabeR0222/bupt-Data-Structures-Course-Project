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
    @Select("select * from articleScores where article_id=#{articleId}")
    ArticleScore findByUserId(Integer articleId);

    @Select("select * from articleScores")
    List<ArticleScore> getAllScores();

    @Update("update articleScores set score = (#{score}) where (article_id = #{articleId}, score = #{score})")
    void update(int userId, int articleId, int score);

    @Insert("insert into articleScores(user_id, article_id, score) values(#{userId}, #{articleId}, #{score})")
    void insert(int userId, int articleId, int score);
}
