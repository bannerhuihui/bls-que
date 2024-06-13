package com.bls.que.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Template implements Serializable {

    private static final long serialVersionUID = -5073304776287207460L;

    private Integer id;

    private String tmpType;

    private String subType;

    private String content;

    private String lable;

}