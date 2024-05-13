package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import java.util.List;

public interface LocationService {
    //获取推荐查询
    List<Location> getLocation();

    //用户主动查询地点
    List<Location> getQueryLocation(LocationQuery query);
}
