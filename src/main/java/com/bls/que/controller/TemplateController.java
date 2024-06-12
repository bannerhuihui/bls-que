package com.bls.que.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.mapper.TemplateMapper;
import com.bls.que.pojo.Template;
import com.bls.que.service.TemplateService;
import com.bls.que.vo.TemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: TemplateController
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/5 11:30
 * @version: 1.0
 */
@RestController
@RequestMapping("/hyd")
public class TemplateController {


    @Autowired
    private TemplateService templateService;

    //@CrossOrigin(origins = "http://localhost:59001")
    @RequestMapping("/genReport/{orderNo}")
    public TemplateVo genReport(@PathVariable("orderNo") String orderNo,String type){
        return templateService.genReport(orderNo,type);
    }



}
