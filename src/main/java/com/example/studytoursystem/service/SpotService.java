package com.example.studytoursystem.service;

import com.example.studytoursystem.model.Spot;
import com.example.studytoursystem.model.SpotQuery;

import java.util.List;

public interface SpotService {
    List<Spot> getSpot(SpotQuery spotQuery);
}
