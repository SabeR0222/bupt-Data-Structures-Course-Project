package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.LocationBrowseCount;
import com.example.studytoursystem.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LocationBrowseCountMapper {
    @Select("select * from location_browse_counts where user_id = #{userid}")
    LocationBrowseCount findByUserId(Integer userId);

    @Select("select * from location_browse_counts where location_id = #{locationId}")
    LocationBrowseCount findByLocation(Integer locationId);
    @Select("select * from location_browse_counts")
    List<LocationBrowseCount> getAllLocationBrowseCounts();

    @Insert("insert into location_browse_counts(user_id, location_id, count) values(#{userId}, #{locationId}, #{count})")
    void add(int userId, int locationId, int count);

    @Update("update location_browse_counts set count = (#{count}) where (location_id = #{locationId}) and (user_id = #{userId})")
    void update(int userId, int locationId, int count);

}
