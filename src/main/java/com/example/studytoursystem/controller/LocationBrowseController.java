package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationBrowseCount;
import com.example.studytoursystem.model.LocationBrowsePlus;
import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.service.LocationBrowseCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locationBrowse")
public class LocationBrowseController {
    @Autowired
    private LocationBrowseCountService locationBrowseCountService;
    @GetMapping("/getLocationBrowseCount/{locationId}")
    public Result<Integer> getLocationBrowseCount(@PathVariable Integer locationId) {
        System.out.println("locationId: " + locationId);
        Integer res = locationBrowseCountService.getLocationBrowseCount(locationId);
        return Result.success(res);
    }

    @PostMapping ("/setCount")
    public Result setLocationBrowseCount(@RequestBody LocationBrowsePlus locationBrowsePlus) {
        locationBrowseCountService.setLocationBrowseCount(locationBrowsePlus);
        System.out.println(locationBrowsePlus);
        return Result.success();
    }
}
