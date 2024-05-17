package com.bls.que.controller;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.vo.LayData;
import com.bls.que.vo.LayPage;
import com.bls.que.vo.PageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: TestController
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/17 10:26
 * @version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/getArray")
    @ResponseBody
    public LayPage<Map<String,Object>> test(Integer page, Integer limit){
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String,Object> m = new HashMap<>();
            m.put("id",i);
            m.put("username","US"+i);
            m.put("sex", i %2 == 0 ?"男":"女");
            m.put("city",i %2 == 0 ?"长春":"蘑菇屯");
            m.put("sign","签名"+i);
            m.put("experience",i+ 100);
            list.add(m);
        }
        LayPage<Map<String,Object>> layPage = new LayPage<>();
        layPage.setCode(200);
        layPage.setMsg("访问成功！");
        layPage.setCount(10);
        layPage.setData(list);
        return layPage;
    }
}
