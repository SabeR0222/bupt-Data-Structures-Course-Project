package com.example.studytoursystem.service;

import com.example.studytoursystem.model.PathQuery;

public interface PathService {
    int[] getPath(PathQuery query);
}
