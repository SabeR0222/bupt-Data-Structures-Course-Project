package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.PathMapper;
import com.example.studytoursystem.mapper.VertexMapper;
import com.example.studytoursystem.model.Graph;
import com.example.studytoursystem.model.PathQuery;
import com.example.studytoursystem.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Dijkstra;

@Service
public class PathServiceImpl implements PathService {
    @Autowired
    private PathMapper pathMapper;

    @Autowired
    private VertexMapper vertexMapper;

    @Override
    public int[] getPath(PathQuery query) {
        int[] path = null;
        Graph graph = new Graph(vertexMapper.getVertex(), pathMapper.getEdge());
        if(query.getDestIdList().size() == 1){
            Dijkstra dijkstra = new Dijkstra(graph, query.getSrcId(), query.getDestIdList().get(0), query.getStrategy());
            path = dijkstra.dijkstra();
        }
        for (int i = 0; i < path.length; i++) {
            System.out.println(path[i]);
        }
        return path;
    }
}
