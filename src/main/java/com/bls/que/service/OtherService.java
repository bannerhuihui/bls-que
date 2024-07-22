package com.bls.que.service;

import com.bls.que.bean.SyncOrderBean;
import com.bls.que.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface OtherService {


    Map<String, Object> syncOrder(SyncOrderBean syncOrderBean);

    Map<String, Object> syncGoods(List<Goods> goodsList);
}

