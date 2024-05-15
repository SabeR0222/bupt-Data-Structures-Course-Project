package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Edge;
import com.example.studytoursystem.model.Vertex;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VertexMapper {
    @Select("select * from vertexes")
    List<Vertex> getVertex();

    @Select("select * from edges")
    List<Edge> getEdge();
}
