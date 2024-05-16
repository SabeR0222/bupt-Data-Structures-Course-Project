package com.example.studytoursystem.service.impl;

import ch.qos.logback.core.read.ListAppender;
import com.example.studytoursystem.mapper.LocationBrowseCountMapper;
import com.example.studytoursystem.model.LocationBrowseCount;
import com.example.studytoursystem.service.LocationBrowseCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationBrowseCountServicelmpl implements LocationBrowseCountService {
    @Autowired
    public LocationBrowseCountMapper locationBrowseCountMapper;


    @Override
    public Map<Integer, Integer> findByUserId(Integer userId) {
        List<LocationBrowseCount> locationBrowseCounts = locationBrowseCountMapper.getAllLocationBrowseCounts();
        Map<Integer, Integer> res = new HashMap<>();
        for (LocationBrowseCount locationBrowseCount : locationBrowseCounts) {
            if (locationBrowseCount.getUserId() == userId) {
                res.put(locationBrowseCount.getLocationId(), locationBrowseCount.getCount());
            }
        }
        return res;
    }

    @Override
    public Integer getLocationBrowseCount(Integer locationId) {
        Integer res = 0;
        List<LocationBrowseCount> locationBrowseCounts = locationBrowseCountMapper.getAllLocationBrowseCounts();
        for (LocationBrowseCount locationBrowseCount : locationBrowseCounts) {
            if (locationBrowseCount.getLocationId() == locationId) {
                res += locationBrowseCount.getCount();
            }
        }
        return res;
    }


    @Override
    public void update(Integer userId, Integer location, Integer score) {
        locationBrowseCountMapper.update(userId, location, score);
    }
}
