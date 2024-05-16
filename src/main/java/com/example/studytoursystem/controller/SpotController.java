package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.model.Spot;
import com.example.studytoursystem.model.SpotQuery;
import com.example.studytoursystem.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpotController {
    @Autowired
    private SpotService spotService;

    @PostMapping("/spot")
    public Result<List<Spot>> getSpot(@RequestBody SpotQuery spotQuery) {
        List<Spot> list = spotService.getSpot(spotQuery);
        return Result.success(list);
    }
}
