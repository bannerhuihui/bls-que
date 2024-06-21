package com.bls.que.mapper;

import com.bls.que.pojo.Sys;

public interface SysMapper {


    int insertSelective(Sys record);

    Sys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sys record);

    Sys selectByName(String name);

}