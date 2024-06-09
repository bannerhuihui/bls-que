package com.bls.que.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @projectName: bls-que
 * @package: com.bls.que.vo
 * @className: TemplateVo
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/7 10:25
 * @version: 1.0
 */
@Data
public class TemplateVo {

    private String userName;

    private String sex;

    private String orderNo;

    private JSONObject pageMessage;

}
