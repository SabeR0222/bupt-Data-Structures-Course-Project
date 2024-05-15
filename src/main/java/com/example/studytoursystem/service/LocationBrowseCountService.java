package com.example.studytoursystem.service;

import java.util.List;
import java.util.Map;

public interface LocationBrowseCountService {

    Map<Integer, Integer> findByUserId(Integer userId);

    Integer getLocationBrowseCount(Integer locationId);

    //更新用户地点的浏览量
    void update(Integer userId, Integer location, Integer count);
}
