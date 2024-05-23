package com.bls.que.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.service.HistoryService;
import com.bls.que.stat.BaseStatic;
import com.bls.que.vo.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: HistoryServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/7 15:07
 * @version: 1.0
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public int createdHistory(History history) {
        if(checkHistory(history)){
            //生成一个关联问题ID
            history.setQuestionId(createdQuestionId());
            //将状态改为可用状态
            history.setQuestionState("可用");
            //编译可访问的html路径
            history.setQuestionUrl(BaseStatic.BASE_URL+history.getQuestionId());
            //添加第一次创建时间
            history.setCreatedTime(new Date());
            history.setUpdatedTime(history.getCreatedTime());
            //生成一个订单ID
            if(StrUtil.isEmpty(history.getOrderId())){
                String orderId = getOrderId();
                history.setOrderId(orderId);
            }
            historyMapper.insertSelective(history);
            return 1;
        }
        return 0;
    }

    private String getOrderId() {
        String orderId = "";
        boolean flag = true;
        while (flag) {
            String baseOrderId = IdUtil.simpleUUID();
            orderId = baseOrderId.substring(0, 3) + baseOrderId.substring(5, 10) + baseOrderId.substring(9, 16);
            History checkHistory = historyMapper.selectByOrderId(orderId);
            if (checkHistory == null) {
                flag = false;
            }
        }
        return orderId;
    }

    @Override
    public PageEntity<History> queryHistoryList(History history) {
        history = initHistory(history);
        List<History> historyList = historyMapper.queryHistoryList(history);
        int totalCount = historyMapper.queryHistoryListCount(history);
        PageEntity<History> pageEntity = new PageEntity<>(historyList,history.getCurrentPage(),history.getMaxRow(),totalCount);
        return pageEntity;
    }

    @Override
    public History queryHistoryById(int id) {
        return historyMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updatedHistory(History history) {
        history = updInitHistory(history);
        int i = historyMapper.updateByPrimaryKeySelective(history);
        //用户的状态修改为未使用状态
        if(StrUtil.equals(history.getQuestionState(),"可用")){
            //查询用户是不是已经填写过调查报告
            Question question = questionMapper.selectByQueId(history.getQuestionId());
            if(question != null){
                //这个是用户已经填写过的单子，需要将单子的状态改为不可用
                question.setQueState(2);
                question.setUpdatedTime(new Date());
                questionMapper.updateByPrimaryKeySelective(question);
            }
        }
        return (i > 0) ? "success":"false";
    }

    @Override
    public boolean queryHistoryByQueId(String queId) {
        History history = historyMapper.queryHistoryBuQueId(queId);
        return history != null;
    }

    private History updInitHistory(History history) {
        if(history == null){
            history = new History();
        }
        history.setUserId(null);
        history.setQuestionUrl(null);
        history.setQuestionId(null);
        history.setCreatedTime(null);
        return history;
    }

    private History initHistory(History history) {
        if(history == null){
            history = new History();
            return history;
        }
        if(history.getCurrentPage() == null || history.getCurrentPage() == 0){
            history.setCurrentPage(1);
        }
        if(history.getMaxRow() == null || history.getMaxRow() == 0){
            history.setMaxRow(10);
        }
        if(StrUtil.isEmpty(history.getOrderId())){
            history.setOrderId(null);
        }
        //计算index
        history.setIndex((history.getCurrentPage() -1) * history.getMaxRow() );
        history.setId(null);
        history.setUpdatedTime(null);
        if(history.getBeginTime() != null ){
            if(history.getEndTime() == null){
                history.setEndTime(new Date());
            }
        }
        //判断所有的字段
        if (StrUtil.isEmpty(history.getQuestionUrl())){
            history.setQuestionUrl(null);
        }
        if( StrUtil.isEmpty(history.getQuestionState())){
            history.setQuestionState(null);
        }
        if(StrUtil.isEmpty(history.getOtherName())){
            history.setOtherName(null);
        }
        if(StrUtil.isEmpty(history.getOrderType())){
            history.setOrderType(null);
        }
        if(StrUtil.isEmpty(history.getOrderRecipient())){
            history.setOrderRecipient(null);
        }
        if(StrUtil.isEmpty(history.getOrderPhone())){
            history.setOrderPhone(null);
        }
        if(StrUtil.isEmpty(history.getQuestionUrl())){
            history.setQuestionUrl(null);
        }
        if (StrUtil.isEmpty(history.getQuestionState())){
            history.setQuestionState(null);
        }
        return history;
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

    private boolean checkHistory(History history) {
        boolean flag = true;
        if(history == null){
            flag = false;
        }
        if(history.getUserId() == null || history.getUserId() == 0){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderType())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderProvince())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderCity())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderCounty())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderAddress())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderRecipient())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderPhone())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOtherName())){
            flag = false;
        }
        return flag;
    }

    private String createdOrder() {
        String orderNo = "";
        boolean flag = true;
        while (flag) {
            int uuid = RandomUtil.randomInt(1000000, 9999999);
            orderNo = DateUtil.format(new Date(), "yyyyMMddHHmm") + uuid;
            History order = historyMapper.selectByOrderId(orderNo);
            if (order == null) {
                flag = false;
            }
        }
        return orderNo;
    }

}
