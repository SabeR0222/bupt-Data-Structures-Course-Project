package com.example.studytoursystem.controller;

import com.example.studytoursystem.model.Result;
import com.example.studytoursystem.utils.ThreadLocalContent;
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
        String username = loginData.get("username");
        String password = loginData.get("password");
        User user = userService.findByUsername(username);
        if(user == null) {
            return Result.error("用户名或密码错误");
        }
        if(password.equals(user.getPassword())){
            ThreadLocalContent.setData(user.getUserId());
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/logout")
    public Result logout(){
        ThreadLocalContent.removeData();
        return Result.success();
    }

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
