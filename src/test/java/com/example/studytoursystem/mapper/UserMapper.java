package com.example.studytoursystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.studytoursystem.model.User;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into users (username,password) values (#{username},#{password})")
    void add(String username, String password);
}
