package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.LocationMapper;
import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.model.User;
import com.example.studytoursystem.service.LocationBrowseCountService;
import com.example.studytoursystem.service.LocationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.BoyerMooreChinese;
import utils.HeapSort;
import utils.UserBasedCF;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationMapper locationMapper;

//    @Autowired
//    private LocationBrowseCountService locationBrowseCountService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Location> getLocation() {
        return locationMapper.getAllLocation();
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

    }

    @Override
    public List<Location> getQueryLocation(LocationQuery query){
        List<Location> allLocation = locationMapper.getAllLocation();
        List<Location> res = locationMapper.getAllLocation();
        if (query.getName() != null) {
            for(Location location : allLocation){
                BoyerMooreChinese boyerMooreChinese = new BoyerMooreChinese(query.getName());
                if(boyerMooreChinese.search(location.getName()) >= location.getName().length()){
                    res.remove(location);
                }
            }
        }
        if (query.getKeyword() != null) {
            Iterator<Location> iterator = res.iterator();
            while (iterator.hasNext()) {
                Location location = iterator.next();
                if (!location.getKeyword().equals(query.getKeyword()) ) {
                    iterator.remove();
                }
            }
        }
        if (query.getKeyword() != null) {
            Iterator<Location> iterator = res.iterator();
            while (iterator.hasNext()) {
                Location location = iterator.next();
                if (location.getType() != query.getType()) {
                    iterator.remove();
                }
            }
        }
        if(query.getSortOrder() != null){
            HeapSort<Location> heapSort = new HeapSort<>();
            if(query.getSortOrder() == 1){
                heapSort.sort(res, (o1, o2) -> o2.getPopularity() - o1.getPopularity());
            }else{
                heapSort.sort(res, (o1, o2) -> o2.getEvaluation() - o1.getEvaluation());
            }
        }

        List<Location> res2 = new ArrayList<>();
        for(int i = 0; i < (res.size() > 10 ? 10 : res.size()); i++){
            res2.add(res.get(i));
        }

        return res2;
    }
}

