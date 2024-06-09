package com.bls.que.service;

import com.bls.que.vo.TemplateVo;

public interface TemplateService {

    TemplateVo genReport(String orderNo,String type);

}
