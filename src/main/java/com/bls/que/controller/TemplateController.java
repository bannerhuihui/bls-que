package com.bls.que.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.mapper.TemplateMapper;
import com.bls.que.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private TemplateMapper templateMapper;

    @RequestMapping("/genReport/{type}")
    public JSONObject genReport(@PathVariable("type") String type){
        if(StrUtil.isNotEmpty(type)){
            List<Template> templates = templateMapper.selectByType(type);
            if(ArrayUtil.isNotEmpty(templates)){
                //随机返回一条数据
                Template template = templates.get(0);
                JSONObject jsonObject = JSONObject.parseObject(template.getContent());
                JSONObject res = new JSONObject();
                res.put(type,jsonObject);
                return res;
            }
        }
        return null;
    }

}
