package com.bls.que.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

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
public class TemplateVo implements Serializable {

    private static final long serialVersionUID = -8323266094190684333L;

    private String userName;

    private String sex;

    private String orderNo;

    private String key;

    private JSONObject pageMessage;

}
