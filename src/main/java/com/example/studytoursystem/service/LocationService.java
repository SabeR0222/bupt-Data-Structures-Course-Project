package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.model.User;

import java.util.List;

public interface LocationService {
    //获取推荐查询
    List<Location> getLocation();

    List<Location> getRecommendLocation(Integer userId);

    //用户主动查询地点
    List<Location> getQueryLocation(LocationQuery query);
}
