package com.bls.que.controller;

import cn.hutool.core.util.StrUtil;
import com.bls.que.bean.SyncOrderBean;
import com.bls.que.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: OtherController
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/13 16:50
 * @version: 1.0
 */
@RestController
@RequestMapping("/hyd")
public class OtherController {


    @Autowired
    private OtherService otherService;

    //第三方订单同步接口
    @RequestMapping("/syncOrder")
    public Map<String,Object> syncOrder(SyncOrderBean syncOrderBean){
        return otherService.syncOrder(syncOrderBean);
    }
}
