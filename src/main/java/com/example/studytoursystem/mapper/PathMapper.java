package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Edge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PathMapper {
    @Select("select * from edges")
    List<Edge> getEdge();
}
