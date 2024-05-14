package com.bls.que.mapper;

import com.bls.que.pojo.History;

import java.util.List;

public interface HistoryMapper {

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History record);

    History selectByQuestionId(String questionId);

    List<History> queryHistoryList(History history);

    int queryHistoryListCount(History history);

    History selectByOrderId(String orderId);

    History queryHistoryBuQueId(String queId);
}