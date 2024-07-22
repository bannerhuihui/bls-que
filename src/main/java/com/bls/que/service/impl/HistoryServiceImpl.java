package com.bls.que.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.bean.SyncOrderFDBean;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.mapper.SysMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.pojo.Sys;
import com.bls.que.service.HistoryService;
import com.bls.que.stat.BaseStatic;
import com.bls.que.vo.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: HistoryServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/7 15:07
 * @version: 1.0
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SysMapper sysMapper;

    @Override
    public int createdHistory(History history) {
        if(checkHistory(history)){
            //所有的orderId作为系统生成的ID
            history.setOrderId(getOrderId());
            //生成一个关联问题ID
            history.setQuestionId(createdQuestionId());
            //将状态改为可用状态
            history.setQuestionState("未填写");
            //编译可访问的html路径
            history.setQuestionUrl(BaseStatic.BASE_URL+history.getQuestionId());
            //添加第一次创建时间
            history.setCreatedTime(new Date());
            history.setUpdatedTime(history.getCreatedTime());
            //生成一个订单ID
            if(StrUtil.isEmpty(history.getOrderId())){
                String orderId = getOrderId();
                history.setOrderId(orderId);
            }
            if(history.getPriceBefore() == null){
                history.setPriceBefore(0);
            }
            history.setOrderProvince("待定");
            history.setOrderCity("待定");
            history.setOrderCounty("待定");
            history.setOrderAddress("待定");
            history.setOrderRecipient("待定");
            history.setOrderPhone("待定");
            history.setOrderRemark("待定");
            if(checkOrderId(history.getOrderId())){
                historyMapper.insertSelective(history);
                return 1;
            }
        }
        return 0;
    }

    private String getOrderId() {
        String orderId = "";
        boolean flag = true;
        while (flag) {
            // 生成时间戳部分，使用当前时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());
            // 生成随机数部分，使用随机数生成器生成6位随机数
            Random random = new Random();
            int randomNumber = random.nextInt(900000) + 100000; // 生成6位随机数
            // 拼接时间戳和随机数生成订单号
            orderId = "HYD-"+timestamp + randomNumber;
            History checkHistory = historyMapper.selectByOrderId(orderId);
            if (checkHistory == null) {
                flag = false;
            }
        }
        return orderId;
    }

    @Override
    public PageEntity<History> queryHistoryList(History history) {
        history = initHistory(history);
        List<History> historyList = historyMapper.queryHistoryList(history);

        int totalCount = historyMapper.queryHistoryListCount(history);
        PageEntity<History> pageEntity = new PageEntity<>(historyList,history.getCurrentPage(),history.getMaxRow(),totalCount);
        return pageEntity;
    }

    @Override
    public History queryHistoryById(int id) {
        return historyMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updatedHistory(History history) {
        history = updInitHistory(history);
        int i = historyMapper.updateByPrimaryKeySelective(history);
        //用户的状态修改为未使用状态
        if(StrUtil.equals(history.getQuestionState(),"未填写")){
            //查询用户是不是已经填写过调查报告
            Question question = questionMapper.selectByQueId(history.getQuestionId());
            if(question != null){
                //这个是用户已经填写过的单子，需要将单子的状态改为不可用
                question.setQueState(2);
                question.setUpdatedTime(new Date());
                questionMapper.updateByPrimaryKeySelective(question);
            }
        }
        return (i > 0) ? "success":"false";
    }

    @Override
    public boolean queryHistoryByQueId(String queId) {
        History history = historyMapper.queryHistoryBuQueId(queId);
        return history != null;
    }

    @Override
    public Map<String,String> syncOrderToFD(Integer id) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("code","error");
        resultMap.put("msg","服务器异常！，请稍后再试！");
        if(id != null && id != 0){
            //获取订单信息
            History history = historyMapper.selectByPrimaryKey(id);
            if(history != null){
                //TODO 对接FD系统
                SyncOrderFDBean fd = new SyncOrderFDBean();
                fd.setTs(new Date().getTime()/1000);//时间戳
                fd.setCno("HongYunDuo");//渠道商编号
                fd.setTotalamount(history.getOrderMoney()+".00");//订单总金额.00
                fd.setOrderid(history.getOrderId());//渠道商平台内不重复的订单编号
                fd.setPhone(history.getOrderPhone());//收货人电话
                fd.setRemark(history.getOrderRemark());//备注
                fd.setConsignee(history.getOrderRecipient());//收货人姓名
                fd.setProvince(history.getOrderProvince());//省
                fd.setCity(history.getOrderCity());//市
                fd.setDistrict(history.getOrderCounty());//区
                fd.setDetailaddress(history.getOrderProvince()+history.getOrderCity()+history.getOrderCounty()+history.getOrderAddress());//详细地址  黑龙江省大兴安岭地区加格达奇大杨树镇国美百货家店
                fd.setIscollecion(history.getOrderMoney()-history.getPriceBefore() == 0 ? 0 : 1);//是否货到付款。1为是，0为否（不传此参数为否）
                fd.setCollectionmoney(history.getPriceBefore());////预收款金额（不传此参数为0）
                Sys sys = sysMapper.selectByName(history.getOrderType() + history.getOrderMoney());
                JSONArray goodsList = null;
                if(sys != null){
                    goodsList = JSONArray.parseArray(sys.getMessage());
                }
                fd.setGoods(goodsList);
                fd.setSign(sign(fd));//签名
                String result = HttpRequest.post("http://api.rtyouth.com/openapi/syncorder.ashx").header(Header.CONTENT_TYPE,"application/json").body(JSONObject.toJSONString(fd)).execute().body();
                //更新订单状态为已推送
                JSONObject json = null;
                try{
                    json = JSONObject.parseObject(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(json != null){
                    if(json.get("Result").equals("ok")){
                        history.setSyncOrder("已通知发货");
                        history.setUpdatedTime(new Date());
                        historyMapper.updateByPrimaryKeySelective(history);
                        resultMap.put("code","success");
                    }else {
                        resultMap.put("code","error");
                        resultMap.put("msg",String.valueOf(json.get("Msg")));
                    }
                }
            }
        }
        return resultMap;
    }

    private History updInitHistory(History history) {
        if(history == null){
            history = new History();
        }
        history.setUserId(null);
        history.setQuestionUrl(null);
        history.setQuestionId(null);
        history.setCreatedTime(null);
        return history;
    }

    private History initHistory(History history) {
        if(history == null){
            history = new History();
            return history;
        }
        if(history.getCurrentPage() == null || history.getCurrentPage() == 0){
            history.setCurrentPage(1);
        }
        if(history.getMaxRow() == null || history.getMaxRow() == 0){
            history.setMaxRow(10);
        }
        if(StrUtil.isEmpty(history.getOrderId())){
            history.setOrderId(null);
        }
        //计算index
        history.setIndex((history.getCurrentPage() -1) * history.getMaxRow() );
        history.setId(null);
        history.setUpdatedTime(null);
        if(history.getBeginTime() != null ){
            if(history.getEndTime() == null){
                history.setEndTime(new Date());
            }
        }
        //判断所有的字段
        if (StrUtil.isEmpty(history.getQuestionUrl())){
            history.setQuestionUrl(null);
        }
        if( StrUtil.isEmpty(history.getQuestionState())){
            history.setQuestionState(null);
        }
        if(StrUtil.isEmpty(history.getOtherName())){
            history.setOtherName(null);
        }
        if(StrUtil.isEmpty(history.getOrderType())){
            history.setOrderType(null);
        }
        if(StrUtil.isEmpty(history.getOrderRecipient())){
            history.setOrderRecipient(null);
        }
        if(StrUtil.isEmpty(history.getOrderPhone())){
            history.setOrderPhone(null);
        }
        if(StrUtil.isEmpty(history.getQuestionUrl())){
            history.setQuestionUrl(null);
        }
        if (StrUtil.isEmpty(history.getQuestionState())){
            history.setQuestionState(null);
        }
        return history;
    }

    private String createdQuestionId() {
        String questionId = "";
        boolean flag = true;
        while (flag) {
            String BaseUserId = IdUtil.simpleUUID();
            questionId = BaseUserId.substring(0, 3) + BaseUserId.substring(5, 10) + BaseUserId.substring(9, 16);
            History checkHistory = historyMapper.selectByQuestionId(questionId);
            if (checkHistory == null) {
                flag = false;
            }
        }
        return questionId;
    }

    private boolean checkHistory(History history) {
        boolean flag = true;
        if(history == null){
            flag = false;
        }
        if(history.getUserId() == null || history.getUserId() == 0){
            flag = false;
        }
        /*if(StrUtil.isEmpty(history.getOrderProvince())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderCity())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderCounty())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderAddress())){
            flag = false;
        }*/
        /*if(StrUtil.isEmpty(history.getOrderRecipient())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderPhone())){
            flag = false;
        }*/
        if(StrUtil.isEmpty(history.getOtherName())){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderId())){
            flag = false;
        }
        if(history.getOrderMoney() == null ){
            flag = false;
        }
        if(StrUtil.isEmpty(history.getOrderType())){
            flag = false;
        }

        return flag;
    }

    private String createdOrder() {
        String orderNo = "";
        boolean flag = true;
        while (flag) {
            int uuid = RandomUtil.randomInt(1000000, 9999999);
            orderNo = DateUtil.format(new Date(), "yyyyMMddHHmm") + uuid;
            History order = historyMapper.selectByOrderId(orderNo);
            if (order == null) {
                flag = false;
            }
        }
        return orderNo;
    }


    private boolean checkOrderId(String orderId){
        History order = historyMapper.selectByOrderId(orderId);
        if(order == null){
            return true;
        }
        return false;
    }

    private String sign(SyncOrderFDBean fd){
        String sign = "";
        if(fd != null){
            //cno\ts\totalamount\phone\orderid
            //cno\orderid\phone\totalamount\ts
            //String baseUrl = "cno="+fd.getCno()+"&ts="+fd.getTs()+"&totalamount="+fd.getTotalamount()+"&phone="+fd.getPhone()+"&orderid="+fd.getOrderid()+"&key=9ekcoxe23cmjp60411rg8vo0gh";

            String baseUrl = "cno="+fd.getCno()+"&orderid="+fd.getOrderid()+"&phone="+fd.getPhone()+"&totalamount="+fd.getTotalamount()+"&ts="+fd.getTs()+"&key=9ekcoxe23cmjp60411rg8vo0gh";
            sign = MD5.create().digestHex(baseUrl);
        }
        return sign;
    }

}
