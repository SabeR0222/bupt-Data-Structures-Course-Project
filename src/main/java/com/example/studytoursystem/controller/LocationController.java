package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public Result<List<Location>> getLocation(){
        List<Location> list = locationService.getLocation();
        return Result.success(list);
    }

    @GetMapping("/recommend/{userId}")
    public Result<List<Location>> getRecommendLocation(@PathVariable Integer userId){
        List<Location> list = locationService.getRecommendLocation(userId);
        return Result.success(list);
    }

    @GetMapping("/query")
    public Result<List<Location>> getQueryLocation(@ModelAttribute LocationQuery query){
        System.out.println(query);
        List<Location> list = locationService.getQueryLocation(query);
        return Result.success(list);
    }
}
