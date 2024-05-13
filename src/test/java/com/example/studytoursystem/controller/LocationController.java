package com.example.studytoursystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.service.LocationService;
import java.util.List;


@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public Result<List<Location>> getAllLocation(){
        List<Location> list = locationService.getLocation();
        return Result.success(list);
    }

    @PostMapping("/query")
    public Result<List<Location>> getQueryLocation(@RequestBody LocationQuery query){
        System.out.println(query);
        List<Location> list = locationService.getQueryLocation(query);
        return Result.success(list);
    }
}
