package com.bls.que.service;

import com.bls.que.pojo.User;

import java.util.Map;

public interface UserService {
    User login(User user);

    String createdUser(User user);

    User selectUserByKey(Integer userId);

    String logout(Integer userId);

}
