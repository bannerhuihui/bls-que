package com.bls.que.controller;

import cn.hutool.core.util.StrUtil;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.pojo.User;
import com.bls.que.service.HistoryService;
import com.bls.que.service.UserService;
import com.bls.que.vo.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: HistoryController
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/7 15:20
 * @version: 1.0
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionMapper questionMapper;

    //创建问卷订单
    @PostMapping("/createdHistory")
    public String createdHistory(History history){
        int isTrue = historyService.createdHistory(history);
        if(isTrue > 0){
            return "success";
        }
        return "false";
    }

    //创建问卷订单
    @PostMapping("/queryHistoryList")
    @ResponseBody
    public PageEntity<History> queryHistoryList(History history){
        PageEntity<History> pageEntity = historyService.queryHistoryList(history);
        return pageEntity;
    }


    //更新数据回显
    @GetMapping("/queryById")
    @ResponseBody
    public History queryById(int id){
        return historyService.queryHistoryById(id);
    }


    @PostMapping(value = "updatedHistory")
    @ResponseBody
    public Map updatedHistory(History history){
        String questionId = history.getQuestionId();
        Map res = new HashMap<>();
        User user = userService.selectUserByKey(history.getUserId());
        String s = historyService.updatedHistory(history);
        //需要更新question表
        Question question = questionMapper.selectByQueId1(questionId);
        String his_orderType = "";
        String que_orderType = "";
        String que_second = "";
        if(question != null){
            String label = question.getLabel();
            if(StrUtil.isNotEmpty(label)){
                String [] strs = label.split("_");
                que_orderType = strs[0];
                que_second = strs[1];
            }
            if(StrUtil.equals("高血压",history.getOrderType())){
                his_orderType = "GXY";
            }else if(StrUtil.equals("高血脂",history.getOrderType())){
                his_orderType = "GXZ";
            }else if(StrUtil.equals("高血糖",history.getOrderType())){
                his_orderType = "GXT";
            }else if(StrUtil.equals("肠道",history.getOrderType())){
                his_orderType = "CD";
            }else if(StrUtil.equals("免疫力",history.getOrderType())){
                his_orderType = "ZHMY";
            }
            if(!StrUtil.equals(his_orderType,que_orderType)){
                question.setLabel(his_orderType+"_"+que_second);
                question.setTmpContent(null);
                questionMapper.updateByPrimaryKeySelective(question);
            }
        }
        res.put("msg",s);
        res.put("userId",user.getId());
        res.put("userName",user.getUserName());
        return res;
    }

}
