package com.bls.que.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Question {

    private Integer id;

    private String queId;

    private Integer queState;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String gender;

    private String height;

    private String weight;

    private String waist;

    private String otherText;

    private String label;

    private Date createdTime;

    private Date updatedTime;

    private String userName;

    private String tmpContent;
}