package com.bls.que.mapper;

import com.bls.que.pojo.Question;

public interface QuestionMapper {

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    Question selectByQueId(String queId);

    Question selectByQueId1(String queId);
}