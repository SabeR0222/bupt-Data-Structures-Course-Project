package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.VertexMapper;
import com.example.studytoursystem.model.Vertex;
import com.example.studytoursystem.service.VertexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VertexSeriveImpl implements VertexService {
    @Autowired
    private VertexMapper vertexMapper;

    @Override
    public List<Vertex> getVertex() {
        return vertexMapper.getVertex();
    }
}
