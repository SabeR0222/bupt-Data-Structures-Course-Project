package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.User;
import com.example.studytoursystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Integer login(Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        List<User> users = userMapper.getAllUsers();
        User user1 = null;
        for(User user : users){
            if(user.getUsername().equals(username)){
                user1 = user;
                if(password.equals(user1.getPassword())){
                    return user1.getUserId();
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);
    }
}
