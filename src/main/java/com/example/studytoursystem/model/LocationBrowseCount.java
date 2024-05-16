package com.example.studytoursystem.model;

import lombok.Data;

@Data
public class LocationBrowseCount {
    Integer locationId;
    Integer userId;
    Integer count;
}
