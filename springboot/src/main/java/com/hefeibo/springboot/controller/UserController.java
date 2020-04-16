package com.hefeibo.springboot.controller;

import com.hefeibo.springboot.bean.Result;
import com.hefeibo.springboot.bean.User;
import com.hefeibo.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    public Result regist(User user){
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @RequestMapping(value = "/login")//get和post都支持
    //@PostMapping(value = "/login")//只支持post
    public Result login(User user){
        return userService.login(user);
    }
    @PostMapping(value = "/delete")
    public Result delete(User user){
        return userService.delete(user);
    }
}