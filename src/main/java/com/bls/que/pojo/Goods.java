package com.bls.que.pojo;

import lombok.Data;

@Data
public class Goods {
    private Integer id;

    private String name;

    private String gid;

    private String goodsType;

    private Integer price;

    private Integer weight;

    private Integer totalCount;

    private String status;

    private String remark;

}