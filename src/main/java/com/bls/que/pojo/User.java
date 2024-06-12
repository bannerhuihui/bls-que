package com.bls.que.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String userName;

    private String passWord;

    private String phone;

    private Date createdTime;

    private Date updatedTime;

    private Date loginTime;

    private Date outTime;

    private String remark;

    private String auth;

}