package com.bls.que.mapper;

import com.bls.que.pojo.User;

public interface UserMapper {

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User login(User user);

    User loginByPhone(User user);

    User selectByUserName(String userName);
}