package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.ArticleMapper;
import com.example.studytoursystem.mapper.ArticleScoreMapper;
import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.*;
import com.example.studytoursystem.utils.*;
import com.example.studytoursystem.service.ArticleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServicelmpl implements ArticleService {
    @Autowired
    ArticleScoreMapper articleScoreMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(int userId, String title, String content, int locationId) {
        HashMap<Character, String> huffmanCodes = Codes(content);
        byte[] content1 = compression(huffmanCodes,content);
        String huffmanCodesJson = huffmanCodesJson(huffmanCodes);
        articleMapper.add(userId, title, content1, huffmanCodesJson, locationId);
    }

    @Override
    public void delete(int articleId) {
        articleMapper.delete(articleId);
    }

    @Override
    public boolean update(int articleId, int userId, String title, String content, int locationId) {
        Integer CurrentUserId = ThreadLocalContent.getData();
        if (CurrentUserId != userId) {
            return false;
        }
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
    public List<SimplifiedArticle> recommendArticle() {
        List<ArticleScore> articleScores = articleScoreMapper.getAllScores();
        Map<Integer, Map<Integer, Integer>> IdScoreMap = new HashMap<>();
        HeapSort<ArticleScore> heapSort = new HeapSort<>();
        heapSort.sort(articleScores, (o1,o2) ->o1.getUserId() - o2.getUserId());
        Integer userId = null;
        Map<Integer, Integer> scoreMap = new HashMap<>();
        for(ArticleScore articleScore : articleScores) {
            if(userId == null || !userId.equals(articleScore.getUserId())){
                if(!scoreMap.isEmpty()){
                    IdScoreMap.put(userId, scoreMap);
                    scoreMap = new HashMap<>();
                }
                userId = articleScore.getUserId();
            }
            scoreMap.put(articleScore.getArticleId(), articleScore.getScore());
        }
        IdScoreMap.put(userId, scoreMap);
        Integer currentUserId = ThreadLocalContent.getData();
        List<SimplifiedArticle> res = null;
        List<Map.Entry<Integer, Double>> userBasedCF = new UserBasedCF(IdScoreMap).recommend(currentUserId);
        for(Map.Entry<Integer, Double> entry : userBasedCF){
            Article article = articleMapper.findByArticleId(entry.getKey());
            if(article != null){
                res.add(simplifyArticle(article));
            }
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
