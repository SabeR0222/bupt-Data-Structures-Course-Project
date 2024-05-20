package com.example.studytoursystem.utils;

import com.example.studytoursystem.model.Location;

import java.util.*;

public class LocationBasedRecommend{
    public static List<Location>  LocationBasedRecommend(Map<Integer, Integer> userViewCounts, List<Location> locations) {
        List<Location> recommendations = new ArrayList<>();

        // 将地点按照类型和关键词分类
        Map<String, List<Location>> locationMap = new HashMap<>();
        for (Location location : locations) {
            String key = location.getType() + "-" + location.getKeyword();
            locationMap.computeIfAbsent(key, k -> new ArrayList<>()).add(location);
        }

        // 对用户浏览次数进行排序，按照浏览次数从高到低排序
        List<Map.Entry<Integer, Integer>> sortedList = new ArrayList<>(userViewCounts.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 获取浏览次数最高的前几个地点的类型和关键词，并基于此进行推荐
        int numRecommendations = Math.min(10, sortedList.size()); // 设定推荐的最大数量为10个
        for (int i = 0; i < numRecommendations; i++) {
            Integer locationId = sortedList.get(i).getKey();
            for (Location location : locations) {
                if (location.getLocationId().equals(locationId)) {
                    String key = location.getType() + "-" + location.getKeyword();
                    List<Location> similarLocations = locationMap.get(key);
                    if (similarLocations != null) {
                        for (Location similarLocation : similarLocations) {
                            if (!similarLocation.getLocationId().equals(locationId) && !recommendations.contains(similarLocation.getLocationId())) {
                                recommendations.add(similarLocation);
                                if (recommendations.size() >= 5) {
                                    return recommendations;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        return recommendations;
    }
}
