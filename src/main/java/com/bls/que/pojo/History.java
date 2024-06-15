package com.bls.que.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class History {

    private Integer id;

    private Integer userId;

    private String questionUrl;

    private String questionState;

    private String orderId;

    private String orderType;

    private String orderProvince;

    private String orderCity;

    private String orderCounty;

    private String orderAddress;

    private String orderRecipient;

    private String orderPhone;

    private String orderRemark;

    private String questionId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private Date updatedTime;

    private String remark;

    private String otherName;

    private Integer orderMoney;

    private Integer priceBefore;

    /** 起始页 */
    private Integer currentPage;
    /** 起始索引值 */
    private Integer index;
    /** 每页数量 */
    private Integer maxRow;
    /** 总页数 */
    private Integer totalPage;
    /** 问题创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime ;
    /** 问题更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime ;

    private String syncOrder;

}