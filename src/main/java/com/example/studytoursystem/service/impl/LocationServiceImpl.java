package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.LocationMapper;
import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;

    @Override
    public List<Location> getLocation() {
        return locationMapper.getAllLocation();
    }

    @Override
    public List<Location> getQueryLocation(LocationQuery query) {
        return locationMapper.getAllLocation();
    }
}
