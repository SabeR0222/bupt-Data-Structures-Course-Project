package com.example.studytoursystem.service;


import com.example.studytoursystem.model.User;

import java.util.Map;

public interface UserService {
    User findByUsername(String username);

    Integer login(Map<String, String> loginData);

    void register(String username, String password);
}
