package com.example.studytoursystem.utils;

import java.util.*;
import java.util.HashMap;

public class UserBasedCF {
    private Map<Integer, Map<Integer, Integer>> data;

    public UserBasedCF(Map<Integer, Map<Integer, Integer>> data) {
        this.data = data;
    }

    public double similarity(Integer user1, Integer user2) {
        // 计算两个用户的相似度
        Set<Integer> commonItems = new HashSet<>(data.get(user1).keySet());
        commonItems.retainAll(data.get(user2).keySet());

        if (commonItems.isEmpty()) {
            return 0;
        }

        double sumOfSquares = commonItems.stream()
                .mapToDouble(item -> Math.pow(data.get(user1).get(item) - data.get(user2).get(item), 2))
                .sum();

        return 1 / (1 + sumOfSquares);
    }

    public List<Map.Entry<Integer, Double>> recommend(Integer targetUser) {
        // 为目标用户推荐物品
        Map<Integer, Double> scores = new HashMap<>();

        for (Integer user : data.keySet()) {
            if (user.equals(targetUser)) {
                continue;
            }

            double similarityScore = similarity(targetUser, user);

            for (Map.Entry<Integer, Integer> entry : data.get(user).entrySet()) {
                Integer item = entry.getKey();
                Integer rating = entry.getValue();

                if (!data.get(targetUser).containsKey(item) || data.get(targetUser).get(item) == 0) {
                    scores.putIfAbsent(item, 0.0);
                    scores.put(item, scores.get(item) + similarityScore * rating);
                }
            }
        }

        // 排序并返回推荐结果
        List<Map.Entry<Integer, Double>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        return sortedScores;
    }
}