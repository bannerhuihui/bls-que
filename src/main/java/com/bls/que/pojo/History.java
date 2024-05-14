package com.bls.que.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class History {
    /** 主键 */
    private Integer id ;
    /** 用户ID */
    private Integer userId ;
    /** 生成活动路径 */
    private String questionUrl ;
    /** 生成路径状态 */
    private Integer questionState ;
    /** 第三方平台订单号 */
    private String orderId ;
    /** 第三方平台订单类型;订单类型 1、2、3等，代表不同的发货内容 */
    private Integer orderType ;
    /** 省 */
    private String orderProvince ;
    /** 市 */
    private String orderCity ;
    /** 县/区 */
    private String orderCounty ;
    /** 第三方平台配送地址 */
    private String orderAddress ;
    /** 收件人姓名 */
    private String orderRecipient ;
    /** 收件人手机号 */
    private String orderPhone ;
    /** 收件人订单备注 */
    private String orderRemark ;
    /** 关联问题ID */
    private String questionId ;
    /** 问题创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime ;
    /** 问题更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime ;
    /** 问题备注 */
    private String remark ;
    /** 第三方平台名称 */
    private String otherName ;
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

}