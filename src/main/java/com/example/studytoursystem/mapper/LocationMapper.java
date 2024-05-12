package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LocationMapper {
    @Select("select * from locations")
    List<Location> getAllLocation();
}
