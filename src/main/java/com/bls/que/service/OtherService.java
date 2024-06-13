package com.bls.que.service;

import com.bls.que.bean.SyncOrderBean;

import java.util.Map;

public interface OtherService {


    Map<String, Object> syncOrder(SyncOrderBean syncOrderBean);
}

