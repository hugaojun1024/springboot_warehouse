package com.zds.springboot.controller;

import com.zds.springboot.common.Constants;
import com.zds.springboot.common.Result;
import com.zds.springboot.model.User;
import com.zds.springboot.service.IJwtUtil;
import com.zds.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2023-07-05-[上午 9:27]-周三
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    @Qualifier("jJwtUtil")
    IJwtUtil iJwtUtil;

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        User u = userService.login(user);
        if (u != null) {
            String userId = u.getUserId();
            String token = iJwtUtil.createToken(userId);
            System.out.println("token" + token);
            return Result.success("登陆成功",token);
        }
        return Result.error(Constants.CODE_NULL_USERNAME_OR_PASSWORD,"用户名或密码错误");
    }

    //需要验证token
    @RequestMapping("info")
    public String info() {
        return "验证通过";
    }
}
