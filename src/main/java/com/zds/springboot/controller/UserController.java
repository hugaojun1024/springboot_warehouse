package com.zds.springboot.controller;

import cn.hutool.json.JSONObject;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.zds.springboot.common.Constants;
import com.zds.springboot.common.Result;
import com.zds.springboot.model.User;
import com.zds.springboot.service.IJwtUtil;
import com.zds.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("jJwtUtil")
    IJwtUtil iJwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) throws UnirestException {
//        User u = userService.login(user);
//        return Result.success("登陆成功",u);
        User u = userService.login(user);
        if (u != null) {
            String userId = u.getUserId();
            String token = iJwtUtil.createToken(userId);
            System.out.println("token" + token);

            // 创建一个Map或JSONObject来包含token和用户对象u
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", u);

            return Result.success("登陆成功", response);
        }
        return Result.error(Constants.CODE_NULL_USERNAME_OR_PASSWORD,"用户名或密码错误");
    }

    //添加用户
    @PostMapping("")
    public Result saveUser(@RequestBody User user){
        if (userService.saveUser(user)){
            return Result.success();
        }
        return Result.error(Constants.CODE_WRONG_SYSTEM,"出错了，添加或修改失败");
    }

}
