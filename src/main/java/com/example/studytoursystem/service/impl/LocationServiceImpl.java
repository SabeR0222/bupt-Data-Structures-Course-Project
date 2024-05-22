package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.controller.LocationController;
import com.example.studytoursystem.mapper.LocationBrowseCountMapper;
import com.example.studytoursystem.mapper.LocationMapper;
import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationBrowseCount;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.service.LocationService;
import com.example.studytoursystem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationBrowseCountMapper locationBrowseCountMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Location> getLocation() {
        return locationMapper.getAllLocation();


    }

    @Override
    public List<Location> getRecommendLocation(Integer userId) {
//        List<Location> allLocation = locationMapper.getAllLocation();
//        List<Location> res2 = new ArrayList<>();
//        List<User> allUsers = userMapper.getAllUsers();
//        Map<Integer, Map<Integer, Integer>> data = new HashMap<>();
//        for(User user : allUsers) {
//            Map<Integer, Integer> userRating = new HashMap<>();
//            Iterator iterator = user.getArticleScore().entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
//                userRating.put(entry.getKey(), (Integer) entry.getValue());
//            }
//            data.put(user.getUserId(), userRating);
//        }
//        UserBasedCF userBasedCF = new UserBasedCF(currentUser, data);
//
//
//        for(int i = 0; i < (allLocation.size() > 10 ? 10 : allLocation.size()); i++){
//            res2.add(allLocation.get(i));
//        }
//
//        return res2;
        List<LocationBrowseCount> locationBrowseCounts = locationBrowseCountMapper.getAllLocationBrowseCounts();
        Map<Integer, Integer> userViewCounts = new HashMap<>();
        for(LocationBrowseCount locationBrowseCount : locationBrowseCounts) {
            if(locationBrowseCount.getUserId() == userId)
                userViewCounts.put(locationBrowseCount.getLocationId(), locationBrowseCount.getCount());
        }
        List<Location> recommendationLocationId =
                LocationBasedRecommend.LocationBasedRecommend(userViewCounts, locationMapper.getAllLocation());
        List<Location> res2 = new ArrayList<>();
        for(int i = 0; i < (recommendationLocationId.size() > 10 ? 10 : recommendationLocationId.size()); i++){
            res2.add(recommendationLocationId.get(i));
        }
        return recommendationLocationId;
    }


    @Override
    public List<Location> getQueryLocation(LocationQuery query){
        List<Location> allLocation = locationMapper.getAllLocation();
        List<Location> res = locationMapper.getAllLocation();
        if (query.getName() != null) {
            for(Location location : allLocation){
                BoyerMooreChinese boyerMooreChinese = new BoyerMooreChinese(query.getName());
                if(boyerMooreChinese.searchAll(location.getName()).isEmpty()){
                    res.remove(location);
                }
            }
        }
        if (query.getType() != null) {
            Iterator<Location> iterator = res.iterator();
            while (iterator.hasNext()) {
                Location location = iterator.next();
                if (!location.getType().equals(query.getType()) ) {
                    iterator.remove();
                }
            }
        }
        if (query.getKeyword() != null) {
            Iterator<Location> iterator = res.iterator();
            while (iterator.hasNext()) {
                Location location = iterator.next();
                if (!Objects.equals(location.getKeyword(), query.getKeyword())) {
                    iterator.remove();
                }
            }
        }

        if(query.getSortOrder() != null){
            System.out.println("sortOrder" + query.getSortOrder());
            //优先队列实现res的基于热度和评价进行不完全排序
            if (query.getSortOrder() == 1){
                MyPriorityQueue<Location> myPriorityQueue = new MyPriorityQueue<>(new Comparator<Location>() {
                    @Override
                    public int compare(Location o1, Location o2) {
                        if(o1.getPopularity() == o2.getPopularity()){
                            return o1.getEvaluation() - o2.getEvaluation();
                        }
                        return o1.getPopularity() - o2.getPopularity();
                    }
                });
                for(Location location : res){
                    myPriorityQueue.offer(location);
                    if(myPriorityQueue.size() > 10){
                        myPriorityQueue.poll();
                    }
                }
                res = new ArrayList<>();
                while(!myPriorityQueue.isEmpty()){
                    System.out.println(myPriorityQueue.peek().getName());
                    res.add(myPriorityQueue.poll());
                }

            }
            else {
                MyPriorityQueue<Location> myPriorityQueue = new MyPriorityQueue<>(new Comparator<Location>() {
                    @Override
                    public int compare(Location o1, Location o2) {
                        if(o1.getEvaluation() == o2.getEvaluation()){
                            return o1.getPopularity() - o2.getPopularity();
                        }
                        return o1.getEvaluation() - o2.getEvaluation();
                    }
               });
                for(Location location : res){
                    myPriorityQueue.offer(location);
                    if(myPriorityQueue.size() > 10){
                        myPriorityQueue.poll();
                    }
                }
                res = new ArrayList<>();
                while(!myPriorityQueue.isEmpty()){
                    System.out.println(myPriorityQueue.peek().getName());
                    res.add(myPriorityQueue.poll());
                }
            }
            //反转res
            Collections.reverse(res);
        }
        System.out.println(res);
        List<Location> res2 = new ArrayList<>();
        for(int i = 0; i < (res.size() > 10 ? 10 : res.size()); i++){
            res2.add(res.get(i));
        }

        return res2;
    }
}

