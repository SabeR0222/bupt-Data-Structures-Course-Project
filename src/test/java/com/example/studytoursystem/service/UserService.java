package com.example.studytoursystem.service;


import com.example.studytoursystem.model.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);
}
