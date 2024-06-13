package com.bls.que.bean;

import lombok.Data;

/**
 * @projectName: bls-que
 * @package: com.bls.que
 * @className: SyncOrderBean
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/13 16:52
 * @version: 1.0
 */
@Data
public class SyncOrderBean {

    private String uid; //乐好课的用户ID

    private String oid; //乐好课的订单ID

    private Integer pr; //支付金额

    private String spu; //乐好课商品ID   可以用于匹配对应套餐

    private String token; //验证token

}
