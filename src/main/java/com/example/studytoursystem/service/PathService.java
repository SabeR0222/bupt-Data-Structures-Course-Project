package com.example.studytoursystem.service;

import com.example.studytoursystem.model.PathQuery;

import java.util.List;

public interface PathService {
    List<Integer> getPath(PathQuery query);
}
