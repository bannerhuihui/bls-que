package com.bls.que.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @projectName: bls-que
 * @package: com.bls.que.bean
 * @className: SyncOrderFDBean
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/21 13:46
 * @version: 1.0
 */
@Data
public class SyncOrderFDBean implements Serializable {

    private static final long serialVersionUID = -5218063414660748409L;

    private Long ts;

    private String cno;

    private String totalamount;

    private String orderid;

    private String phone;

    private String sign;

    private String remark;

    private String consignee;

    private String province;

    private String city;

    private String district;

    private String detailaddress;

    private Integer iscollecion;

    private Integer collectionmoney;

    private List<Map<String,Object>> goods;

}
