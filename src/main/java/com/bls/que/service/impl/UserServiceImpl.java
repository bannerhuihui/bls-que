package com.bls.que.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bls.que.mapper.UserMapper;
import com.bls.que.pojo.User;
import com.bls.que.service.UserService;
import com.bls.que.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: UserServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/7 10:04
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User queryUser = null;
        if (user != null && StrUtil.isNotEmpty(user.getUserName()) && StrUtil.isNotEmpty(user.getPassWord())) {
            queryUser = userMapper.login(user);
        }
        if(queryUser == null){
            queryUser = userMapper.loginByPhone(user);
        }
        if (queryUser != null) {
            //记录登陆时间
            queryUser.setLoginTime(new Date());
            queryUser.setToken(TokenUtil.generateToken(queryUser.getUserName()));
            queryUser.setTokenDate(new Date());
            userMapper.updateByPrimaryKeySelective(queryUser);
        }
        return queryUser;
    }

    @Override
    public String createdUser(User user) {
        if (user != null) {
            user.setId(null);
            user.setCreatedTime(new Date());
            user.setUpdatedTime(user.getCreatedTime());
            user.setRemark("true");
            int i = userMapper.insertSelective(user);
            return i > 0 ? "success" : "false";
        }
        return "false";
    }

    @Override
    public User selectUserByKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String logout(Integer userId) {
        //退出登录，显示修改退出登录时间
        User user = new User();
        if(userId != null && userId != 0){
            user.setId(userId);
            user.setOutTime(new Date());
            user.setTokenDate(DateUtil.offsetMinute(new Date(), -30));
            userMapper.updateByPrimaryKeySelective(user);
            return "success";
        }
        return "false";
    }

    @Override
    public User queryUserByUserName(String userName) {
        User user = null;
        if(StrUtil.isNotEmpty(userName)){
            user = userMapper.selectByUserName(userName);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        if(user != null && user.getId() != null && user.getId() != 0){
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
