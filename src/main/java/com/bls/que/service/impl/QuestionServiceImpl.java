package com.bls.que.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: QuestionServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/14 14:34
 * @version: 1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    @Transactional
    public String createQuestion(Question question) {
        if(checkQuestion(question)){
            //修改营养师历史列表中的数据状态为已完成
            History history = historyMapper.selectByQuestionId(question.getQueId());
            if(history != null){
                question.setCreatedTime(new Date());
                question.setUpdatedTime(question.getCreatedTime());
                //该条数据是最终数据
                question.setQueState(1);
                String label = "ALL";
                if(StrUtil.isNotEmpty(question.getOtherText())){
                    JSONObject parse = JSONObject.parseObject(question.getOtherText());
                    if(parse != null && parse.get("defecation") != null){
                        System.out.println(parse.get("defecation").getClass());
                        JSONArray jsonArray = (JSONArray) parse.get("defecation");
                        for (Object key : jsonArray) {
                            String strKey = (String) key;
                            if(StrUtil.equals(strKey,"便秘")){
                                label = "BM";
                            } else if (StrUtil.equals(strKey,"经常腹泻")) {
                                label = "FX";
                            }
                        }
                    }

                }
                String historyType = "";
                String firstLabel = "";
                if(StrUtil.isNotEmpty(question.getLabel())){
                    historyType = question.getLabel();
                    if(StrUtil.equals("高血压",question.getLabel())){
                        firstLabel = "GXY";
                    }
                    if(StrUtil.equals("高血脂",question.getLabel())){
                        firstLabel = "GXZ";
                    }
                    if(StrUtil.equals("高血糖",question.getLabel())){
                        firstLabel = "GXT";
                    }
                    if(StrUtil.equals("肠道健康",question.getLabel())){
                        historyType = "肠道";
                        firstLabel = "CD";
                    }
                    if(StrUtil.equals("免疫力",question.getLabel())){
                        firstLabel = "ZHMY";
                    }
                }
                question.setLabel(firstLabel+"_"+label);
                Question question1 = questionMapper.selectByQueId1(question.getQueId());
                if(question1 == null){
                    questionMapper.insertSelective(question);
                    History updHistory = new History();
                    updHistory.setId(history.getId());
                    updHistory.setQuestionState("已填写");
                    updHistory.setOrderType(historyType);
                    updHistory.setOrderRecipient(question.getOrderRecipient());
                    updHistory.setOrderPhone(question.getOrderPhone());
                    updHistory.setOrderProvince(question.getOrderProvince());
                    updHistory.setOrderCity(question.getOrderCity());
                    updHistory.setOrderCounty(question.getOrderCounty());
                    updHistory.setOrderAddress(question.getOrderAddress());
                    updHistory.setOrderRemark(question.getOrderRemark());
                    historyMapper.updateByPrimaryKeySelective(updHistory);
                    return "success";
                }
            }
        }
        return "error";
    }

    @Override
    public Question queryQuestionByQid(String qid) {
        if(StrUtil.isNotEmpty(qid)){
            return questionMapper.selectByQueId(qid);
        }
        return null;
    }

    private boolean checkQuestion(Question question) {
        boolean flag = true;
        if(question == null){
            flag = false;
        }
        return flag;
    }
}
