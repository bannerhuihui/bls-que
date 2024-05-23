package com.bls.que.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String createQuestion(Question question) {
        if(checkQuestion(question)){
            question.setCreatedTime(new Date());
            question.setUpdatedTime(question.getCreatedTime());
            //该条数据是最终数据
            question.setQueState(1);
            //TODO 给数据大标签
            //question.setLabel("待定");
            questionMapper.insertSelective(question);
            //修改营养师历史列表中的数据状态为已完成
            History history = historyMapper.selectByQuestionId(question.getQueId());
            if(history != null){
                History updHistory = new History();
                updHistory.setId(history.getId());
                updHistory.setQuestionState("不可用");
                historyMapper.updateByPrimaryKeySelective(updHistory);
            }
            return "success";
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
