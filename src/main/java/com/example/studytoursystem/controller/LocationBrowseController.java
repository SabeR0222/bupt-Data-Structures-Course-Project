package com.example.studytoursystem.controller;

import com.example.studytoursystem.service.LocationBrowseCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locationBrowse")
public class LocationBrowseController {
    @Autowired
    private LocationBrowseCountService locationBrowseCountService;
    @PostMapping ("/getLocationBrowseCount")
    public Integer getLocationBrowseCount(Integer locationId) {
        return locationBrowseCountService.getLocationBrowseCount(locationId);
    }

    @PostMapping ("/update")
    public void update(Integer userId, Integer location, Integer count) {
        locationBrowseCountService.update(userId, location, count);
    }
}
