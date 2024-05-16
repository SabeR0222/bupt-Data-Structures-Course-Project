package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ArticleMapper {
    @Select("select * from articles where article_id = #{articleId}")
    Article findByArticleId(Integer articleId);

    @Insert("insert into articles(user_id, title, content, location_id) values(#{userId}, #{title}, #{content}, #{locationId})")
    void add(int userId, String title, String content, int locationId, int popularity);

    @Update("update articles set popularity = (#{popularity}) where (article_id = #{articleId})")
    void updatePopularity(int articleId, int popularity);

    @Update("update articles set evaluation = (#{evaluation}) where (artucle_id = #{articleId})")
    void updateEvaluation(int articleId, int evaluation);

    @Update("update articles set user_id = (#{userId}), title = (#{title}), content = (#{content}), location_id = (#{locationId}) where (artucle_id = #{articleId})")
    void update(int articleId, int userId, String title, String content, int locationId);
}
