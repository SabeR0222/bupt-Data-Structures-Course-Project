package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.ArticleMapper;
import com.example.studytoursystem.mapper.ArticleScoreMapper;
import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.*;
import com.example.studytoursystem.utils.*;
import com.example.studytoursystem.service.ArticleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServicelmpl implements ArticleService {
    @Autowired
    ArticleScoreMapper articleScoreMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean add(Integer userId,String title, String content, int locationId) {
        if(content == null){
            return false;
        }
        HashMap<Character, String> huffmanCodes = Codes(content);
        byte[] content1 = compression(huffmanCodes,content);
        String huffmanCodesJson = huffmanCodesJson(huffmanCodes);
        articleMapper.add(userId, title, content1, huffmanCodesJson, locationId);
        return true;
    }

//    @Override
//    public boolean delete(int articleId, int userId) {
//        if (articleMapper.findByArticleId(articleId).getUserId() != userId) {
//            return false;
//        }
//        articleMapper.delete(articleId);
//        articleScoreMapper.delete(articleId);
//        return true;
//    }

    @Override
    public boolean update(int articleId, int userId, String title, String content, int locationId) {
        if(userId != articleMapper.findByArticleId(articleId).getUserId())
            return false;
        HashMap<Character, String> huffmanCodes = Codes(content);
        byte[] content1 = compression(huffmanCodes,content);
        String huffmanCodesJson = huffmanCodesJson(huffmanCodes);
        articleMapper.update(articleId, title, content1, huffmanCodesJson, locationId);
        return true;

    }

    @Override
    public void updatePopularity(int articleId) {
        int popularity = articleMapper.findByArticleId(articleId).getPopularity() + 1;
        articleMapper.updatePopularity(articleId, popularity);
    }

    @Override
    public Article getArticle(int articleId) {
        return articleMapper.findByArticleId(articleId);
    }

    @Override
    public List<SimplifiedArticle> recommendArticle(Integer currentUserId) {
        List<ArticleScore> articleScores = articleScoreMapper.getAllScores();
        Map<Integer, Map<Integer, Integer>> IdScoreMap = new HashMap<>();
        HeapSort<ArticleScore> heapSort = new HeapSort<>();
        heapSort.sort(articleScores, (o1,o2) ->o1.getUserId() - o2.getUserId());
        Integer userId = null;
        Map<Integer, Integer> scoreMap = new HashMap<>();
        for(int i = 0; i < articleScores.size(); i++) {
            ArticleScore articleScore = articleScores.get(i);
            if(userId == null || !userId.equals(articleScore.getUserId())){
                if(!scoreMap.isEmpty()){
                    IdScoreMap.put(userId, scoreMap);
                    scoreMap = new HashMap<>();
                }
                userId = articleScore.getUserId();
            }
            scoreMap.put(articleScore.getArticleId(), articleScore.getScore());
            if(i == articleScores.size() - 1)
                IdScoreMap.put(userId, scoreMap);
        }
        List<SimplifiedArticle> res = new ArrayList<>();
        List<Map.Entry<Integer, Double>> userBasedCF = new UserBasedCF(IdScoreMap).recommend(currentUserId);
        for(Map.Entry<Integer, Double> entry : userBasedCF){
            Article article = articleMapper.findByArticleId(entry.getKey());
            if(article != null){
                res.add(simplifyArticle(article));
            }
        }
        List<Article> articles = articleMapper.getAllArticle();
        for(Article article : articles){
            if(!res.contains(simplifyArticle(article))){
                res.add(simplifyArticle(article));
            }
        }
        return res;
    }

    @Override
    public List<Map.Entry<Integer, Integer>> searchWord(String text, String word) {
        List<Map.Entry<Integer, Integer>> res = new ArrayList<>();
        List<Integer> places = new BoyerMooreChinese(word).searchAll(text);
        Integer n = word.length();
        if(places.isEmpty()){
            return null;
        }
        for(int i = 0; i < places.size(); i++) {
            res.add(new AbstractMap.SimpleEntry<>(places.get(i), i + n));
        }
        return res;
    }


    @Override
    public String getContent(Integer articleId) {
        if(articleMapper.findByArticleId(articleId) != null){
            Article article = articleMapper.findByArticleId(articleId);
            String huffmanCodesJson = article.getHuffmanCodesJson();
            HashMap<Character, String> huffmanCodes = huffmanCodesFromJson(huffmanCodesJson);
            byte[] byteContent = article.getContent();
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < byteContent.length; i++){
                stringBuilder.append((char) (byteContent[i] + '0'));
            }
            String content = HuffmanDecompression.decompress(stringBuilder.toString(), huffmanCodes);
            return content;
        }
        return null;
    }

    @Override
    public SimplifiedArticle getArticleByTitle(String title) {
        List<Article> articles = articleMapper.getAllArticle();
        for(Article article : articles){
            if(article.getTitle().equals(title)){
                return simplifyArticle(article);
            }
        }
        return null;
    }

    @Override
    public List<SimplifiedArticle> getArticleByLocation(Integer locationId) {
        List<Article> articles = articleMapper.getAllArticle();
        List<SimplifiedArticle> res = new ArrayList<>();
        for(Article article : articles){
            if(article.getLocationId() == locationId){
                res.add(simplifyArticle(article));
            }
        }
        return res;
    }

    private HashMap<Character, String> Codes(String content){
        HashMap<Character, String> huffmanCodes = HuffmanCompression.generateHuffmanCodes(content);
        return huffmanCodes;
    };

    private SimplifiedArticle simplifyArticle(Article article){
        return new SimplifiedArticle(article.getArticleId(), article.getUserId(), article.getTitle(), article.getLocationId(), article.getPopularity(), article.getEvaluation());
    }


    private byte[] compression(HashMap<Character, String> huffmanCodes,String content) {
        String compress = HuffmanCompression.compress(content, huffmanCodes);
        byte[] content1 = new byte[compress.length()];
        for(int i = 0; i < compress.length(); i++){
            content1[i] = (byte) (compress.charAt(i) - '0');
        }
        return content1;
    }

    private String huffmanCodesJson(HashMap<Character, String> huffmanCodes){
        // 将哈夫曼编码表转换为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String huffmanCodesJson = null;
        try {
            huffmanCodesJson = objectMapper.writeValueAsString(huffmanCodes);
        } catch (Exception e) {
            System.out.println("JSON序列化失败");
        }

        return huffmanCodesJson;
    };

    private HashMap<Character, String> huffmanCodesFromJson(String huffmanCodesJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Character, String> huffmanCodes = null;
        try {
            huffmanCodes = objectMapper.readValue(huffmanCodesJson, new TypeReference<HashMap<Character, String>>() {});
        } catch (Exception e) {
            System.out.println("JSON反序列化失败");
        }
        return huffmanCodes;
    }
}
