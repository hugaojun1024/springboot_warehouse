package com.zds.springboot.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.zds.springboot.common.Constants;
import com.zds.springboot.common.Result;
import com.zds.springboot.model.User;
import com.zds.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) throws UnirestException {
        User u = userService.login(user);
        return Result.success("登陆成功",u);
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
