package com.bls.que.controller;

import com.bls.que.pojo.History;
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
        Map res = new HashMap<>();
        User user = userService.selectUserByKey(history.getUserId());
        String s = historyService.updatedHistory(history);
        res.put("msg",s);
        res.put("userId",user.getId());
        res.put("userName",user.getUserName());
        return res;
    }

}
