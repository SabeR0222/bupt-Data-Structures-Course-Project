package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.model.User;
import com.example.studytoursystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginData) {
        Integer userId = userService.login(loginData);
        if (userId != null) {
            return Result.success(userId);
        }
        return Result.error("用户名或密码错误");
    }

//    @PostMapping("/logout")
//    public Result logout(){
//        return Result.success();
//    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> registerData){
        String username = registerData.get("username");
        String password = registerData.get("password");

        User user = userService.findByUsername(username);
        if(user == null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("用户名已被占用");
        }
    }


}
