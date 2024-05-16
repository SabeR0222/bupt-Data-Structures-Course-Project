package com.example.studytoursystem.mapper;

import com.example.studytoursystem.model.Location;
import com.example.studytoursystem.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username}")
    User findByUsername(String username);


    @Select("select * from users")
    List<User> getAllUsers();

    @Insert("insert into users (username,password) values (#{username},#{password})")
    void add(String username, String password);
}
