package com.bls.que.service;

import com.bls.que.pojo.History;
import com.bls.que.vo.PageEntity;

import java.util.Map;

public interface HistoryService {
    int createdHistory(History history);

    PageEntity<History> queryHistoryList(History history);

    History queryHistoryById(int id);

    String updatedHistory(History history);

    boolean queryHistoryByQueId(String queId);

    Map<String,String> syncOrderToFD(Integer id);

}
