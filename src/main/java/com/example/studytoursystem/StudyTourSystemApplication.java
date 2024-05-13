package com.example.studytoursystem;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.LocationQuery;
import com.example.studytoursystem.service.impl.LocationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudyTourSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyTourSystemApplication.class, args);
    }

}
