package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.LocationBrowseCountMapper;
import com.example.studytoursystem.model.ArticleScore;
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
        System.out.println(res);
        return res;
    }



    @Override
    public void setLocationBrowseCount(LocationBrowseCount locationBrowseCount) {
        List<LocationBrowseCount> locationBrowseCounts = locationBrowseCountMapper.getAllLocationBrowseCounts();
        for(LocationBrowseCount locationBrowseCount1 : locationBrowseCounts){
            if(locationBrowseCount1.getUserId() == locationBrowseCount.getUserId() && locationBrowseCount1.getLocationId() == locationBrowseCount.getLocationId()) {
                locationBrowseCountMapper.update(locationBrowseCount.getUserId(), locationBrowseCount.getLocationId(), locationBrowseCount.getCount());
                return;
            }
        }
        locationBrowseCountMapper.add(locationBrowseCount.getUserId(), locationBrowseCount.getLocationId(), locationBrowseCount.getCount());
    }
}
