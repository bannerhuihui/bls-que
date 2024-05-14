package com.bls.que.controller;

import com.bls.que.pojo.User;
import com.bls.que.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.invoke.MethodType;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: UserController
 * @author: huihui
 * @description: TODO
 * @date: 2024/4/30 11:03
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public User login(User user){
        return userService.login(user);
    }


    @RequestMapping(value = "createdUser",method = RequestMethod.POST)
    public String createdUser(User user){
        return userService.createdUser(user);
    }

}
