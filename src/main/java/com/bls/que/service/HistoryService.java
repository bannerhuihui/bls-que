package com.bls.que.service;

import com.bls.que.pojo.History;
import com.bls.que.vo.PageEntity;

public interface HistoryService {
    int createdHistory(History history);

    PageEntity<History> queryHistoryList(History history);

    History queryHistoryById(int id);

    String updatedHistory(History history);

    boolean queryHistoryByQueId(String queId);

    String syncOrderToFD(Integer id);

}
