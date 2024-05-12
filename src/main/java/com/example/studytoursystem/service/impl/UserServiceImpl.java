package com.example.studytoursystem.service.impl;

import com.example.studytoursystem.mapper.UserMapper;
import com.example.studytoursystem.model.User;
import com.example.studytoursystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);
    }
}
