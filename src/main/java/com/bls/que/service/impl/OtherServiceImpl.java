package com.bls.que.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.bls.que.bean.SyncOrderBean;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.service.OtherService;
import com.bls.que.stat.BaseStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: OtherServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/13 17:14
 * @version: 1.0
 */
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public Map<String, Object> syncOrder(SyncOrderBean syncOrderBean) {
        Map<String,Object> result = new HashMap<>();
        if(syncOrderBean != null){
            if(StrUtil.isNotEmpty(syncOrderBean.getOid())
                    && syncOrderBean.getPr() != null
                    && StrUtil.isNotEmpty(syncOrderBean.getSpu())
                    && StrUtil.isNotEmpty(syncOrderBean.getUid())
                    && StrUtil.isNotEmpty(syncOrderBean.getToken())
            ){
                History history = new History();
                history.setUserId(2);
                history.setOrderId(syncOrderBean.getOid());
                history.setPriceBefore(syncOrderBean.getPr());
                //TODO 根据Uid生成类型、订单价格
                history.setOrderType("免疫力");
                history.setOrderMoney(4080);
                history.setOrderProvince("待定");
                history.setOrderCity("待定");
                history.setOrderCounty("待定");
                history.setOrderAddress("待定");
                history.setOrderRecipient("待定");
                history.setOrderPhone("待定");
                history.setOrderRemark("待定");
                history.setOtherName("其他");

                history.setQuestionId(createdQuestionId());
                //将状态改为可用状态
                history.setQuestionState("未填写");
                //编译可访问的html路径
                history.setQuestionUrl(BaseStatic.BASE_URL+history.getQuestionId());
                //添加第一次创建时间
                history.setCreatedTime(new Date());
                history.setUpdatedTime(history.getCreatedTime());
                if(checkOrderId(history.getOrderId())){
                    historyMapper.insertSelective(history);
                    result.put("code",2000);
                    result.put("message","订单同步完成");
                    result.put("data",history.getQuestionUrl());
                }else {
                    result.put("code",2001);
                    result.put("message","订单号重复");
                    result.put("data",syncOrderBean);
                }
            }else {
                result.put("code",4001);
                result.put("message","参数不全");
                result.put("data",syncOrderBean);
            }
        }else {
            result.put("code",4000);
            result.put("message","参数为空");
            result.put("data",null);
        }
        return result;
    }

    private String createdQuestionId() {
        String questionId = "";
        boolean flag = true;
        while (flag) {
            String BaseUserId = IdUtil.simpleUUID();
            questionId = BaseUserId.substring(0, 3) + BaseUserId.substring(5, 10) + BaseUserId.substring(9, 16);
            History checkHistory = historyMapper.selectByQuestionId(questionId);
            if (checkHistory == null) {
                flag = false;
            }
        }
        return questionId;
    }

    private boolean checkOrderId(String orderId){
        History order = historyMapper.selectByOrderId(orderId);
        if(order == null){
            return true;
        }
        return false;
    }
}
