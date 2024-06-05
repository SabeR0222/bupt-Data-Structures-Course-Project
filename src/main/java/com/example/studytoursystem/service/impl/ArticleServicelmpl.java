package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.ArticleMapper;
import com.example.studytoursystem.mapper.ArticleScoreMapper;
import com.example.studytoursystem.mapper.LocationMapper;
import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.*;
import com.example.studytoursystem.utils.*;
import com.example.studytoursystem.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServicelmpl implements ArticleService {
    @Autowired
    ArticleScoreMapper articleScoreMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    LocationMapper locationMapper;

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

    @Override
    public boolean delete(int articleId, int userId) {
//        if (articleMapper.findByArticleId(articleId).getUserId() != userId) {
//            return false;
//        }
        articleScoreMapper.delete(articleId);
        articleMapper.delete(articleId);
        return true;
    }

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
        if(IdScoreMap.get(currentUserId) != null){
            List<Map.Entry<Integer, Double>> userBasedCF = new UserBasedCF(IdScoreMap).recommend(currentUserId);
            for(Map.Entry<Integer, Double> entry : userBasedCF){
                Article article = articleMapper.findByArticleId(entry.getKey());
                if(article != null){
                    res.add(simplifyArticle(article));
                }
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
            res.add(new AbstractMap.SimpleEntry<>(places.get(i), places.get(i) + n));
        }
        return res;
    }


    @Override
    public String getContent(Integer articleId) {
        if(articleMapper.findByArticleId(articleId) != null){
            Article article = articleMapper.findByArticleId(articleId);
            String huffmanCodesJson = article.getHuffmanCodes();
            HashMap<Character, String> huffmanCodes = huffmanCodesFromJson(huffmanCodesJson);
            byte[] byteContent = article.getContent();
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < byteContent.length; i++){
                stringBuilder.append((char) (byteContent[i] + '0'));
            }
            String content = HuffmanDecompression.decompress(stringBuilder.toString(), huffmanCodes);
            return content.replace("\\", "\n");
        }
        return null;
    }

    @Override
    public List<SimplifiedArticle> getArticleByTitle(String title) {
        MyHashMap<String, List<Article>> articleMap = new MyHashMap<>();

        List<Article> articles = articleMapper.getAllArticle();
        for (Article article : articles) {
            if (!articleMap.containsKey(article.getTitle())) {
                articleMap.put(article.getTitle(), new ArrayList<>());
            }
            articleMap.get(article.getTitle()).add(article);
        }

        List<Article> foundArticles = articleMap.getOrDefault(title, Collections.emptyList());
        return foundArticles.stream()
                .map(this::simplifyArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedArticle> getArticleByLocation(String locationName) {
        List<Location> locations = locationMapper.getAllLocation();
        Integer locationId = 0;
        for(Location location : locations){
            if(location.getName().equals(locationName)){
                locationId = location.getLocationId();
                break;
            }
        }
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
        List<Location> locations = locationMapper.getAllLocation();
        String locationName = "";
        for(Location location : locations){
            if(location.getLocationId() == article.getLocationId())
                locationName = location.getName();
        }
        List<User> users = userMapper.getAllUsers();
        String userName = "";
        for(User user : users){
            if(user.getUserId() == article.getUserId())
                userName = user.getUsername();
        }
        return new SimplifiedArticle(article.getArticleId(), userName, article.getTitle(), locationName, article.getPopularity(), article.getEvaluation());
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
        HashMap<Character, String> huffmanCodes = new HashMap<>();

        // 分割字符串，得到键值对的列表
        String[] pairs = huffmanCodesJson.split(",");

        // 遍历键值对列表
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];

            // 分割键和值
            String[] keyValue = pair.split(":");
            char key = ' ';
            if(i == 0){
                key = keyValue[0].charAt(2);
            }
            else
                key = keyValue[0].charAt(1);
            String value = keyValue[1].substring(1, keyValue[1].length() - 1);
            if(i == pairs.length - 1)
                value = value.substring(0, value.length() - 1);
            // 将键值对添加到HashMap中
            huffmanCodes.put(key, value.toString());
        }
        return huffmanCodes;
    }
}
