package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;

import java.util.List;

public interface LocationService {
    List<Location> getLocation();

    List<Location> getQueryLocation(LocationQuery query);
}
