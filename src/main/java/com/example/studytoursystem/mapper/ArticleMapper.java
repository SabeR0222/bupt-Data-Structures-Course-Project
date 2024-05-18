package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select * from articles where article_id = #{articleId}")
    Article findByArticleId(Integer articleId);

    @Insert("insert into articles(user_id, title, content, huffman_codes, location_id) values(#{userId}, #{title}, #{huffmanCodes}, #{locationId})")
    void add(int userId, String title, byte[] content1, String huffmanCodes, int locationId);

    @Update("update articles set popularity = (#{popularity}) where (article_id = #{articleId})")
    void updatePopularity(int articleId, int popularity);

    @Update("update articles set evaluation = (#{evaluation}) where (artucle_id = #{articleId})")
    void updateEvaluation(int articleId, double evaluation);

    @Delete("delete from articles where article_id = #{articleId}")
    void delete(int articleId);

    @Select("select *  from articles")
    List<Article> getAllArticle();

    @Update("update articles set title = (#{title}), content = (#{content}), huffman_codes = (#{huffmanCodes}),location_id = (#{locationId}) where (article_id = #{articleId})")
    void update(int articleId, String title, byte[] content, String huffmanCodes, int locationId);
}
