package com.bls.que.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.QuestionMapper;
import com.bls.que.mapper.TemplateMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Question;
import com.bls.que.pojo.Template;
import com.bls.que.service.TemplateService;
import com.bls.que.vo.TemplateVo;
import com.bls.que.vo.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @projectName: bls-que
 * @package: com.bls.que.service.impl
 * @className: TemplateServiceImpl
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/7 10:30
 * @version: 1.0
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private HistoryMapper historyMapper;


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public TemplateVo genReport(String orderNo,String type) {
        History history = null;
        if(StrUtil.isNotEmpty(orderNo)){
            //根据orderNo获取订单信息
            history = historyMapper.selectByOrderId(orderNo);
        }
        String tmpType = "";
        String subType = "";
        String userName = "";
        String gender = "";
        //在历史记录中有信息，获取问卷信息
        if(history != null){
            Question question = questionMapper.selectByQueId(history.getQuestionId());
            if(StrUtil.isNotEmpty(question.getLabel())){
                String[] labels = question.getLabel().split("_");
                tmpType = labels[0];
                subType = labels[1];
            }
            userName = question.getUserName();
            gender = question.getGender();
        }
        if(StrUtil.isNotEmpty(type)){
            tmpType = type;
        }
        //将数据保存到Vo
        TemplateVo templateVo = new TemplateVo();
        if(StrUtil.isNotEmpty(tmpType) && StrUtil.isNotEmpty(subType) && StrUtil.isNotEmpty(userName) && StrUtil.isNotEmpty(gender)){
            templateVo.setUserName(userName);
            templateVo.setSex(StrUtil.equals("男",gender) ? "先生":"女士");
            templateVo.setOrderNo(orderNo);
            templateVo.setKey(tmpType);
            //随机封装页面内容
            //-------------第一层开始--------------
            FirstData firstData = new FirstData();
            List firstList = new ArrayList();
            FirstFirstData ffData1 = new FirstFirstData();
            ffData1.setTitle("饮食方案");
            List<Item> firstFFList1 = new ArrayList<>();
            Item firstItem1 = new Item();
            Item firstItem2 = new Item();
            firstItem1.setContent(" ");
            firstItem2.setContent(" ");
            Template first1 = new Template();
            first1.setTmpType(tmpType);
            first1.setSubType("ysfa");
            List<Template> firstTemplates1 = templateMapper.selectByTemplate(first1);
            Template firstTemplate1 = getIndex(firstTemplates1);
            if(firstTemplate1 != null){
                firstItem1.setContent(firstTemplate1.getContent().substring(0,44));
                firstItem2.setContent(firstTemplate1.getContent().substring(44));
            }
            //TODO 这里是不是需要AI
            firstItem1.setPrompt("");
            //TODO 这里是不是需要AI
            firstItem2.setPrompt("");
            firstFFList1.add(firstItem1);
            firstFFList1.add(firstItem2);
            ffData1.setParagraph(firstFFList1);
            firstList.add(ffData1);

            FirstFirstData ffData2 = new FirstFirstData();
            ffData2.setTitle("运动方案");
            List<Item> firstFFList2 = new ArrayList<>();
            Item firstItem3 = new Item();
            firstItem3.setContent(" ");
            Template first2 = new Template();
            first2.setTmpType(tmpType);
            first2.setSubType("ydfa");
            List<Template> firstTemplates2 = templateMapper.selectByTemplate(first2);
            Template firstTemplate2 = getIndex(firstTemplates2);
            if(firstTemplate2 != null){
                firstItem3.setContent(firstTemplate2.getContent());
            }
            //TODO 这里是不是需要AI
            firstItem3.setPrompt("");
            firstFFList2.add(firstItem3);
            ffData2.setParagraph(firstFFList2);
            firstList.add(ffData2);

            FirstFirstData ffData3 = new FirstFirstData();
            ffData3.setTitle("睡眠方案");
            List<Item> firstFFList3 = new ArrayList<>();
            Item firstItem4 = new Item();
            firstItem4.setContent(" ");
            Template first3 = new Template();
            first3.setTmpType(tmpType);
            first3.setSubType("smfa");
            List<Template> firstTemplates3 = templateMapper.selectByTemplate(first3);
            Template firstTemplate3 = getIndex(firstTemplates3);
            if(firstTemplate3 != null){
                firstItem4.setContent(firstTemplate3.getContent());
            }
            //TODO 这里是不是需要AI
            firstItem4.setPrompt("");
            firstFFList3.add(firstItem4);
            ffData3.setParagraph(firstFFList3);
            firstList.add(ffData3);

            FirstFirstData ffData4 = new FirstFirstData();
            ffData4.setTitle("心理方案");
            List<Item> firstFFList4 = new ArrayList<>();
            Item firstItem5 = new Item();
            firstItem5.setContent(" ");
            Template first4 = new Template();
            first4.setTmpType(tmpType);
            first4.setSubType("xlfa");
            List<Template> firstTemplates4 = templateMapper.selectByTemplate(first4);
            Template firstTemplate4 = getIndex(firstTemplates4);
            if(firstTemplate4 != null){
                firstItem5.setContent(firstTemplate4.getContent());
            }
            //TODO 这里是不是需要AI
            firstItem5.setPrompt("");
            firstFFList4.add(firstItem5);
            ffData4.setParagraph(firstFFList4);
            firstList.add(ffData4);
            firstData.setSchemes(firstList);
            //-------------第一层结束--------------

            //-------------第二层开始--------------
            SecondData secondData = new SecondData();
            String arrStr = "[[\"1 复合多种营养素片\",\"每天2次，每次1片\",\"早餐/晚餐后温水吞服\"],[\"2 高纤果蔬精华\",\"每天1次，每次1袋\",\"每日午餐前\"],[\"3 灵芝富硒多肽蛋白粉\",\"每天1次，每次1袋\",\"每日早餐后\"]]";
            //换成数据库内容
            Template second = new Template();
            second.setTmpType(tmpType);
            second.setSubType("secondYYFA");
            List<Template> secondTemplates = templateMapper.selectByTemplate(second);
            Template secondTemplate = getIndex(secondTemplates);
            if (secondTemplate != null) {
                secondData.setTable(JSONArray.parseArray(secondTemplate.getContent()));
            }
            //-------------第二层结束--------------

            //-------------第三层开始--------------
            ThirdData thirdData = new ThirdData();
            List thirdList = new ArrayList();
            List<Item> thirdList1 = new ArrayList<>();
            List<Item> thirdList2 = new ArrayList<>();
            List<Item> thirdList3 = new ArrayList<>();
            List<Item> thirdList4 = new ArrayList<>();
            //高血脂
            if(StrUtil.equals(tmpType,"GXZ")) {
                Item thirdItem1 = new Item();
                //根据temType和subType来查找（gdnr2）
                Template third1 = new Template();
                third1.setTmpType(tmpType);
                third1.setSubType("gdnr2");
                List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
                Template thirdTemplate1 = getIndex(thirdTemplates1);
                if (thirdTemplate1 != null) {
                    thirdItem1.setContent(thirdTemplate1.getContent());
                }
                //thirdItem1.setContent("免疫力低下会有哪些症状表现呢？最明显的就是身体抵抗力差，容易反复感冒且需要较长时间才能恢复；易过敏，甚至出现过敏性疾病，例如湿疹、荨麻疹、哮喘等。其次，精神状态不佳，常常感到精力不够、疲劳乏力、体虚多汗、失眠、营养不良等。免疫力低下人群不仅呼吸系统疾病频发，例如呼吸道及肺部炎症、过敏性鼻炎等，也会导致消化系统不适，容易出现胃肠道感染、便秘、腹泻、消化不良、食欲不振等症状。相较于正常人而言，免疫力过低者的伤口愈合时间会更长，特别是术后人群，这是由于免疫失守后病菌容易侵入人体，破坏可以让伤口愈合的血小板，导致伤口不易愈合，易感染，身体恢复慢。若长期处于免疫力不足的状态下，免疫细胞无法杀灭和抑制体内的致病菌，致病菌繁殖就会加快，导致疾病快速发展。");
                //TODO 这里是不是需要AI
                thirdItem1.setPrompt("");
                //那什么是高血脂
                thirdList1.add(thirdItem1);
                Item thirdItem2 = new Item();
                Template third2 = new Template();
                third2.setTmpType(tmpType);
                thirdItem2.setContent(" ");
                third2.setSubType("gxz");
                List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
                Template thirdTemplate2 = getIndex(thirdTemplates2);
                if (thirdTemplate2 != null) {
                    thirdItem2.setContent(thirdTemplate2.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem2.setPrompt("");
                Item thirdItem2_1 = new Item();
                thirdItem2_1.setContent("那什么是高血脂症？");
                thirdList2.add(thirdItem2_1);
                thirdList2.add(thirdItem2);
                Item thirdItem3 = new Item();
                Template third3 = new Template();
                thirdItem3.setContent(" ");
                third3.setTmpType(tmpType);
                third3.setSubType("gxzyy");
                List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
                Template thirdTemplate3 = getIndex(thirdTemplates3);
                if (thirdTemplate3 != null) {
                    thirdItem3.setContent(thirdTemplate3.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem3.setPrompt("");
                Item thirdItem3_1 = new Item();
                thirdItem3_1.setContent("那什么是高血脂症？");
                thirdList3.add(thirdItem3_1);
                thirdList3.add(thirdItem3);
                Item thirdItem4 = new Item();
                thirdItem4.setContent("接下来我们来看看怎么调理高血脂？");
                Item thirdItem4_1 = new Item();
                thirdItem4_1.setContent("我们会从五个维度一起了解健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。饮食结构可直接影响血脂水平。部分血脂异常的朋友通过调整饮食和改善生活方式均可降低血脂和脂蛋白，并能维持正常体重，合理的食疗和营养素补充可有效地降低血脂。");
                thirdList4.add(thirdItem4);
                thirdList4.add(thirdItem4_1);
                thirdList.add(thirdList1);
                thirdList.add(thirdList2);
                thirdList.add(thirdList3);
                if(thirdList4.size()  > 0){
                    thirdList.add(thirdList4);
                }
                thirdData.setParagraph(thirdList);
                Item thirdItem5 = new Item();
                Template third5 = new Template();
                third5.setTmpType(tmpType);
                third5.setSubType("gdnr3");
                List<Template> thirdTemplates5 = templateMapper.selectByTemplate(third5);
                Template thirdTemplate5 = getIndex(thirdTemplates5);
                if(thirdTemplate5 != null){
                    thirdItem5.setContent(thirdTemplate5.getContent());
                }
                //TODO 这里是不是需要AI
                thirdData.setScheme1(thirdItem5);
            }else if (StrUtil.equals("CD",tmpType)){
                Item thirdItem1 = new Item();
                //根据temType和subType来查找（gdnr2）
                Template third1 = new Template();
                third1.setTmpType(tmpType);
                third1.setSubType("gdnr2");
                List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
                Template thirdTemplate1 = getIndex(thirdTemplates1);
                if (thirdTemplate1 != null) {
                    thirdItem1.setContent(thirdTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                //那什么是高血脂
                thirdList1.add(thirdItem1);
                Item thirdItem2 = new Item();
                Template third2 = new Template();
                third2.setTmpType(tmpType);
                third2.setSubType("cd");
                List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
                Template thirdTemplate2 = getIndex(thirdTemplates2);
                if (thirdTemplate2 != null) {
                    thirdItem2.setContent(thirdTemplate2.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem2.setPrompt("");
                thirdList2.add(thirdItem2);
                Item thirdItem3 = new Item();
                Template third3 = new Template();
                thirdItem3.setContent(" ");
                third3.setTmpType(tmpType);
                //cdwlyy
                third3.setSubType("cdwlyy");
                List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
                Template thirdTemplate3 = getIndex(thirdTemplates3);
                if (thirdTemplate3 != null) {
                    thirdItem3.setContent(thirdTemplate3.getContent());
                }
                //TODO 这里是不是需要AI
                Item thirdItem3_1 = new Item();
                thirdItem3_1.setContent("肠道功能紊乱怎么导致的呢？");
                thirdList3.add(thirdItem3_1);
                thirdList3.add(thirdItem3);
                Item thirdItem4 = new Item();
                thirdItem4.setContent("接下来我们来看看如何调理肠道功能紊乱，");
                Item thirdItem4_1 = new Item();
                thirdItem4_1.setContent("我们会从五个维度一起了解健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。饮食结构是直接影响肠道健康的因素，合理的食疗和营养素补充可有效地调理肠道。");
                thirdList4.add(thirdItem4);
                thirdList4.add(thirdItem4_1);
                thirdList.add(thirdList1);
                thirdList.add(thirdList2);
                thirdList.add(thirdList3);
                if(thirdList4.size()  > 0){
                    thirdList.add(thirdList4);
                }
                thirdData.setParagraph(thirdList);
                Item thirdItem5 = new Item();
                thirdItem5.setContent("通过改善生活习惯和饮食方式可有效缓解便秘。");
                //TODO 这里是不是需要AI
                thirdData.setScheme1(thirdItem5);
            } else if (StrUtil.equals("ZHMY",tmpType)) {
                Item thirdItem1 = new Item();
                //根据temType和subType来查找（gdnr2）
                Template third1 = new Template();
                third1.setTmpType(tmpType);
                third1.setSubType("gdnr2");
                List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
                Template thirdTemplate1 = getIndex(thirdTemplates1);
                if (thirdTemplate1 != null) {
                    thirdItem1.setContent(thirdTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem1.setPrompt("");
                //那什么是高血脂
                thirdList1.add(thirdItem1);
                Item thirdItem2 = new Item();
                Template third2 = new Template();
                third2.setTmpType(tmpType);
                thirdItem2.setContent(" ");
                third2.setSubType("myldixa");
                List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
                Template thirdTemplate2 = getIndex(thirdTemplates2);
                if (thirdTemplate2 != null) {
                    thirdItem2.setContent(thirdTemplate2.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem2.setPrompt("");
                Item thirdItem2_1 = new Item();
                thirdItem2_1.setContent("免疫力低下会有哪些症状表现呢？");
                thirdList2.add(thirdItem2_1);
                thirdList2.add(thirdItem2);
                Item thirdItem3 = new Item();
                Template third3 = new Template();
                //myldixa
                third3.setTmpType(tmpType);
                third3.setSubType("zhmy");
                List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
                Template thirdTemplate3 = getIndex(thirdTemplates3);
                if (thirdTemplate3 != null) {
                    thirdItem3.setContent(thirdTemplate3.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem3.setPrompt("");
                Item thirdItem3_1 = new Item();
                thirdItem3_1.setContent("综上可见，免疫力低下具有慢性、持续性、易反复的特点，那么，是什么原因导致免疫力低下呢？");
                thirdList3.add(thirdItem3_1);
                thirdList3.add(thirdItem3);
                Item thirdItem4 = new Item();
                thirdItem4.setContent("");
                Item thirdItem4_1 = new Item();
                thirdItem4_1.setContent("接下来我们从营养调理的角度出发，通过五个维度对生理性因素导致的免疫力低下情况制定健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。饮食结构是直接影响免疫健康的因素，合理的食疗和营养素补充可有效地补充免疫力不足。");
                thirdList4.add(thirdItem4);
                thirdList4.add(thirdItem4_1);
                thirdList.add(thirdList1);
                thirdList.add(thirdList2);
                thirdList.add(thirdList3);
                if(thirdList4.size()  > 0){
                    thirdList.add(thirdList4);
                }
                thirdData.setParagraph(thirdList);
                Item thirdItem5 = new Item();
                Template third5 = new Template();
                third5.setTmpType(tmpType);
                third5.setSubType("gdnr3");
                List<Template> thirdTemplates5 = templateMapper.selectByTemplate(third5);
                Template thirdTemplate5 = getIndex(thirdTemplates5);
                if(thirdTemplate5 != null){
                    thirdItem5.setContent(thirdTemplate5.getContent());
                }
                //TODO 这里是不是需要AI
                thirdData.setScheme1(thirdItem5);
            } else if (StrUtil.equals("GXY",tmpType)) {
                Item thirdItem1 = new Item();
                //根据temType和subType来查找（gdnr2）
                Template third1 = new Template();
                third1.setTmpType(tmpType);
                third1.setSubType("gdnr2");
                List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
                Template thirdTemplate1 = getIndex(thirdTemplates1);
                if (thirdTemplate1 != null) {
                    thirdItem1.setContent(thirdTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem1.setPrompt("");
                //那什么是高血脂
                thirdList1.add(thirdItem1);
                Item thirdItem2 = new Item();
                Template third2 = new Template();
                third2.setTmpType(tmpType);
                thirdItem2.setContent(" ");
                third2.setSubType("gxy");
                List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
                Template thirdTemplate2 = getIndex(thirdTemplates2);
                if (thirdTemplate2 != null) {
                    thirdItem2.setContent(thirdTemplate2.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem2.setPrompt("");
                Item thirdItem2_1 = new Item();
                thirdItem2_1.setContent("那什么是高血压？");
                thirdList2.add(thirdItem2_1);
                thirdList2.add(thirdItem2);
                Item thirdItem3 = new Item();
                Template third3 = new Template();
                third3.setTmpType(tmpType);
                third3.setSubType("gxyyy");
                List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
                Template thirdTemplate3 = getIndex(thirdTemplates3);
                if (thirdTemplate3 != null) {
                    thirdItem3.setContent(thirdTemplate3.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem3.setPrompt("");
                Item thirdItem3_1 = new Item();
                thirdItem3_1.setContent("高血压又是如何产生的呢？");
                thirdList3.add(thirdItem3_1);
                thirdList3.add(thirdItem3);
                Item thirdItem4 = new Item();
                thirdItem4.setContent("");
                Item thirdItem4_1 = new Item();
                thirdItem4_1.setContent("接下来我们来看看怎么调理高血压？我们会从五个维度一起了解健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。而健康的饮食对您来说非常重要，具体的饮食定制方案将由专业的营养师为您服务。饮食和生活方式的改变可改善血压状况及减少并发症的风险，生活方式中膳食营养对血压的影响至关重要，而肥胖和饮酒是影响血压水平最主要的因素，合理的食疗和营养素补充可有效地降低血压。");
                thirdList4.add(thirdItem4);
                thirdList4.add(thirdItem4_1);
                thirdList.add(thirdList1);
                thirdList.add(thirdList2);
                thirdList.add(thirdList3);
                if(thirdList4.size()  > 0){
                    thirdList.add(thirdList4);
                }
                thirdData.setParagraph(thirdList);
                Item thirdItem5 = new Item();
                Template third5 = new Template();
                third5.setTmpType(tmpType);
                third5.setSubType("gdnr3");
                List<Template> thirdTemplates5 = templateMapper.selectByTemplate(third5);
                Template thirdTemplate5 = getIndex(thirdTemplates5);
                if(thirdTemplate5 != null){
                    thirdItem5.setContent(thirdTemplate5.getContent());
                }
                //TODO 这里是不是需要AI
                thirdData.setScheme1(thirdItem5);
            }else if (StrUtil.equals("GXT",tmpType)) {
                Item thirdItem1 = new Item();
                //根据temType和subType来查找（gdnr2）
                Template third1 = new Template();
                third1.setTmpType(tmpType);
                third1.setSubType("gdnr2");
                List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
                Template thirdTemplate1 = getIndex(thirdTemplates1);
                if (thirdTemplate1 != null) {
                    thirdItem1.setContent(thirdTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem1.setPrompt("");
                //那什么是高血脂
                thirdList1.add(thirdItem1);
                Item thirdItem2 = new Item();
                Template third2 = new Template();
                third2.setTmpType(tmpType);
                thirdItem2.setContent(" ");
                third2.setSubType("tnb");
                List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
                Template thirdTemplate2 = getIndex(thirdTemplates2);
                if (thirdTemplate2 != null) {
                    thirdItem2.setContent(thirdTemplate2.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem2.setPrompt("");
                Item thirdItem2_1 = new Item();
                thirdItem2_1.setContent("那什么是糖尿病？");
                thirdList2.add(thirdItem2_1);
                thirdList2.add(thirdItem2);
                Item thirdItem3 = new Item();
                Template third3 = new Template();
                third3.setTmpType(tmpType);
                third3.setSubType("tnbyy");
                List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
                Template thirdTemplate3 = getIndex(thirdTemplates3);
                if (thirdTemplate3 != null) {
                    thirdItem3.setContent(thirdTemplate3.getContent());
                }
                //TODO 这里是不是需要AI
                thirdItem3.setPrompt("");
                Item thirdItem3_1 = new Item();
                thirdItem3_1.setContent("高血糖又是如何产生的呢？");
                thirdList3.add(thirdItem3_1);
                thirdList3.add(thirdItem3);
                Item thirdItem4 = new Item();
                thirdItem4.setContent("");
                Item thirdItem4_1 = new Item();
                thirdItem4_1.setContent("接下来我们来看看怎么调理高血糖？我们会从五个维度一起了解健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。而健康的饮食疗法是调理高血糖最基础也是最重要的方法，具体的饮食定制方案将由专业的营养师为您服务。饮食和生活方式的改变可改善血糖状况及减少并发症的风险，合理的食疗和营养素补充可有效地降低血糖。");
                thirdList4.add(thirdItem4);
                thirdList4.add(thirdItem4_1);
                thirdList.add(thirdList1);
                thirdList.add(thirdList2);
                thirdList.add(thirdList3);
                if(thirdList4.size()  > 0){
                    thirdList.add(thirdList4);
                }
                thirdData.setParagraph(thirdList);
                Item thirdItem5 = new Item();
                Template third5 = new Template();
                third5.setTmpType(tmpType);
                third5.setSubType("gdnr3");
                List<Template> thirdTemplates5 = templateMapper.selectByTemplate(third5);
                Template thirdTemplate5 = getIndex(thirdTemplates5);
                if(thirdTemplate5 != null){
                    thirdItem5.setContent(thirdTemplate5.getContent());
                }
                //TODO 这里是不是需要AI
                thirdData.setScheme1(thirdItem5);
            }

            //-------------第三层结束--------------

            //-------------第四层开始--------------
            FourthData fourthData = new FourthData();
            Item fourthItem = new Item();
            fourthItem.setPrompt("优化内容，并重新以列表形式输出");
            //饮食建议方案
            Template fourth = new Template();
            fourth.setTmpType(tmpType);
            fourth.setSubType("ysjyfa");
            if(StrUtil.equals("BM",subType) && StrUtil.equals(tmpType,"CD")){
                fourth.setLable("bm");
            }else if (StrUtil.equals("FX",subType) && StrUtil.equals(tmpType,"CD")){
                fourth.setLable("fx");
            }
            List<Template> fourthTemplates = templateMapper.selectByTemplate(fourth);
            Template fourthTemplate = getIndex(fourthTemplates);
            if(fourthTemplate != null){
                fourthItem.setContent(fourthTemplate.getContent());
            }
            fourthData.setScheme1(fourthItem);
            //-------------第四层结束--------------

            //-------------第五层开始--------------
            FifthData fifthData = new FifthData();
            Item fifthItem1 = new Item();
            //gdnr4 运动建议方案固定内容4
            Template fifth0 = new Template();
            fifth0.setSubType("gdnr4");
            fifth0.setTmpType(tmpType);
            List<Template> fifthTemplates0 = templateMapper.selectByTemplate(fifth0);
            Template fifthTemplate0 = this.getIndex(fifthTemplates0);
            if(fifthTemplate0 != null){
                fifthItem1.setContent(fifthTemplate0.getContent());
            }
            //TODO 这里是不是需要AI
            fifthItem1.setPrompt("");
            fifthData.setScheme2(fifthItem1);
            Item fifthItem2 = new Item();
            //ydxmjy
            Template fifth1 = new Template();
            fifth1.setSubType("ydxmjy");
            fifth1.setTmpType(tmpType);
            List<Template> fifthTemplates1 = templateMapper.selectByTemplate(fifth1);
            Template fifthTemplate1 = this.getIndex(fifthTemplates1);
            if(fifthTemplate1 != null){
                fifthItem2.setContent(fifthTemplate1.getContent());
            }
            //TODO 这里是不是需要AI
            fifthItem2.setPrompt("");
            fifthData.setScheme2YD(fifthItem2);
            Item fifthItem3 = new Item();
            //ydzysx
            Template fifth2 = new Template();
            fifth2.setSubType("ydzysx");
            fifth2.setTmpType(tmpType);
            List<Template> fifthTemplates2 = templateMapper.selectByTemplate(fifth2);
            Template fifthTemplate2 = this.getIndex(fifthTemplates2);
            if(fifthTemplate2 != null){
                fifthItem3.setContent(fifthTemplate2.getContent());
            }
            //TODO 这里是不是需要AI
            fifthItem3.setPrompt("");
            fifthData.setScheme2YDZY(fifthItem3);
            List<Item> fifthList = new ArrayList<>();
            Item fifthItem4 = new Item();
            if(StrUtil.equals("GXZ",tmpType)){
                fifthItem4.setContent("1、保持健康体重及腰围：超重、肥胖特别是腹部型肥胖者比普通人更容易表现为血脂异常，通过减少热量摄入以及适量增加体育锻炼，把体重控制在健康状态。体重正常的BMI值是18.5-23.9kg/m²，控制在这个范围有助于血脂的控制，");
                //TODO 这里是不是需要AI
                fifthItem4.setPrompt("");
                Item fifthItem5 = new Item();
                //jktzjyw
                Template fifth3 = new Template();
                fifth3.setSubType("jktzjyw");
                fifth3.setTmpType(tmpType);
                List<Template> fifthTemplates3 = templateMapper.selectByTemplate(fifth3);
                Template fifthTemplate3 = this.getIndex(fifthTemplates3);
                if(fifthTemplate3 != null){
                    fifthItem5.setContent(fifthTemplate3.getContent());
                }
                fifthList.add(fifthItem4);
                fifthList.add(fifthItem5);
            }else if (StrUtil.equals("CD",tmpType)){
                fifthItem4.setContent("1、保持健康的生活习惯：胃肠功能紊乱、消化不良的情况，往往是由于个人的不良生活习惯导致的。");
                //TODO 这里是不是需要AI
                fifthItem4.setPrompt("");
                Item fifthItem5 = new Item();
                //bcjkdshxg
                Template fifth3 = new Template();
                fifth3.setSubType("bcjkdshxg");
                fifth3.setTmpType(tmpType);
                List<Template> fifthTemplates3 = templateMapper.selectByTemplate(fifth3);
                Template fifthTemplate3 = this.getIndex(fifthTemplates3);
                if(fifthTemplate3 != null){
                    fifthItem5.setContent(fifthTemplate3.getContent());
                }
                fifthList.add(fifthItem4);
                fifthList.add(fifthItem5);
            } else if (StrUtil.equals("ZHMY",tmpType)) {
                fifthItem4.setContent("1、保持健康体重及腰围：肥胖是造成慢性病的重要危险因素之一，通过减少热量摄入以及适量增加体育锻炼，把体重控制在健康状态。体重正常的BMI值是18.5-23.9kg/m²，");
                //TODO 这里是不是需要AI
                fifthItem4.setPrompt("");
                Item fifthItem5 = new Item();
                //bcjkdshxg
                Template fifth3 = new Template();
                fifth3.setSubType("bcjkdshxg");
                fifth3.setTmpType(tmpType);
                List<Template> fifthTemplates3 = templateMapper.selectByTemplate(fifth3);
                Template fifthTemplate3 = this.getIndex(fifthTemplates3);
                if(fifthTemplate3 != null){
                    fifthItem5.setContent(fifthTemplate3.getContent());
                }
                fifthList.add(fifthItem4);
                fifthList.add(fifthItem5);
            } else if (StrUtil.equals("GXY",tmpType)) {
                fifthItem4.setContent("1、保持健康体重及腰围：肥胖是造成高血压的重要危险因素之一，通过减少热量摄入以及适量增加体育锻炼，把体重控制在健康状态。体重正常的BMI值是18.5-23.9kg/m²，控制在这个范围有助于血压的控制，");
                //TODO 这里是不是需要AI
                fifthItem4.setPrompt("");
                Item fifthItem5 = new Item();
                //bcjkdshxg
                Template fifth3 = new Template();
                fifth3.setSubType("bcjktzjyw");
                fifth3.setTmpType(tmpType);
                List<Template> fifthTemplates3 = templateMapper.selectByTemplate(fifth3);
                Template fifthTemplate3 = this.getIndex(fifthTemplates3);
                if(fifthTemplate3 != null){
                    fifthItem5.setContent(fifthTemplate3.getContent());
                }
                fifthList.add(fifthItem4);
                fifthList.add(fifthItem5);
            }else if (StrUtil.equals("GXT",tmpType)) {
                Item fifthItem6 = new Item();
                fifthItem6.setContent("1、定期监测血糖水平");
                fifthList.add(fifthItem6);
            }
            //TODO 这里是不是需要AI
            List<List<Item>> s3List = new ArrayList<>();
            s3List.add(fifthList);
            fifthData.setScheme3(s3List);
            //-------------第五层结束--------------

            //-------------最后一个层--------------
            EndData endData = new EndData();
            List endList = new ArrayList();
            List<Item> endList1 = new ArrayList<>();
            List<Item> endList2 = new ArrayList<>();
            List<Item> endList3 = new ArrayList<>();
            List<Item> endList4 = new ArrayList<>();
            List<Item> endList5 = new ArrayList<>();
            List<Item> endList6 = new ArrayList<>();
            if(StrUtil.equals("GXZ",tmpType)){
                Item endDataItem1 = new Item();
                endDataItem1.setContent("2、保证充足睡眠：");
                Item endDataItem2 = new Item();
                //bzczsm
                Template end1 = new Template();
                end1.setSubType("bzczsm");
                end1.setTmpType(tmpType);
                List<Template> endTemplates1 = templateMapper.selectByTemplate(end1);
                Template endTemplate1 = this.getIndex(endTemplates1);
                if(endTemplate1 != null){
                    endDataItem2.setContent(endTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                Item endDataItem3 = new Item();
                endDataItem3.setContent("3、常运动，勿久坐，保持轻松愉悦的心情。");
                Item endDataItem4 = new Item();
                Template end2 = new Template();
                //cydwjz
                end2.setSubType("cydwjz");
                end2.setTmpType(tmpType);
                List<Template> endTemplates2 = templateMapper.selectByTemplate(end2);
                Template endTemplate2 = this.getIndex(endTemplates2);
                if(endTemplate2 != null){
                    endDataItem4.setContent(endTemplate2.getContent());
                }
                endDataItem4.setPrompt("");

                Item endDataItem5 = new Item();
                endDataItem5.setContent("4、戒烟戒酒。");
                Item endDataItem6 = new Item();
                Template end3 = new Template();
                //jyjj
                end3.setSubType("jyjj");
                end3.setTmpType(tmpType);
                List<Template> endTemplates3 = templateMapper.selectByTemplate(end3);
                Template endTemplate3 = this.getIndex(endTemplates3);
                if(endTemplate3 != null){
                    endDataItem6.setContent(endTemplate3.getContent());
                }
                endDataItem6.setPrompt("");

                Item endDataItem7 = new Item();
                endDataItem7.setContent("5、改善肠道菌群。");
                Item endDataItem8 = new Item();
                Template end4 = new Template();
                //gscdjq
                end4.setSubType("gscdjq");
                end4.setTmpType(tmpType);
                List<Template> endTemplates4 = templateMapper.selectByTemplate(end4);
                Template endTemplate4 = this.getIndex(endTemplates4);
                if(endTemplate4 != null){
                    endDataItem8.setContent(endTemplate4.getContent());
                }
                endDataItem8.setPrompt("");
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                endList1.add(endDataItem1);
                endList1.add(endDataItem2);
                endList2.add(endDataItem3);
                endList2.add(endDataItem4);
                endList3.add(endDataItem5);
                endList3.add(endDataItem6);
                endList4.add(endDataItem7);
                endList4.add(endDataItem8);
                endList.add(endList1);
                endList.add(endList2);
                endList.add(endList3);
                endList.add(endList4);
            } else if (StrUtil.equals("CD",tmpType)) {
                Item endDataItem1 = new Item();
                endDataItem1.setContent("2、改善肠道菌群：");
                Item endDataItem2 = new Item();
                //gscdjq
                Template end1 = new Template();
                end1.setSubType("gscdjq");
                end1.setTmpType(tmpType);
                List<Template> endTemplates1 = templateMapper.selectByTemplate(end1);
                Template endTemplate1 = this.getIndex(endTemplates1);
                if(endTemplate1 != null){
                    endDataItem2.setContent(endTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                endList1.add(endDataItem1);
                endList1.add(endDataItem2);
                endList.add(endList1);
            } else if (StrUtil.equals("ZHMY",tmpType)) {
                Item endDataItem1 = new Item();
                endDataItem1.setContent("2、坚持健康的生活习惯：");
                Item endDataItem2 = new Item();
                //jcjkshxg
                Template end1 = new Template();
                end1.setSubType("jcjkshxg");
                end1.setTmpType(tmpType);
                List<Template> endTemplates1 = templateMapper.selectByTemplate(end1);
                Template endTemplate1 = this.getIndex(endTemplates1);
                if(endTemplate1 != null){
                    endDataItem2.setContent(endTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                Item endDataItem3 = new Item();
                endDataItem3.setContent("3、保持愉悦的心情，适当减轻工作量，避免过度劳累。");
                Item endDataItem4 = new Item();
                Template end2 = new Template();
                //yyxqjqgzl
                end2.setSubType("yyxqjqgzl");
                end2.setTmpType(tmpType);
                List<Template> endTemplates2 = templateMapper.selectByTemplate(end2);
                Template endTemplate2 = this.getIndex(endTemplates2);
                if(endTemplate2 != null){
                    endDataItem4.setContent(endTemplate2.getContent());
                }
                endDataItem4.setPrompt("");
                endList1.add(endDataItem1);
                endList1.add(endDataItem2);
                endList2.add(endDataItem3);
                endList2.add(endDataItem4);
                endList.add(endList1);
                endList.add(endList2);
            } else if (StrUtil.equals("GXY",tmpType)) {
                Item endDataItem1 = new Item();
                endDataItem1.setContent("2、保证充足睡眠：");
                Item endDataItem2 = new Item();
                //bzczsm
                Template end1 = new Template();
                end1.setSubType("bzczsm");
                end1.setTmpType(tmpType);
                List<Template> endTemplates1 = templateMapper.selectByTemplate(end1);
                Template endTemplate1 = this.getIndex(endTemplates1);
                if(endTemplate1 != null){
                    endDataItem2.setContent(endTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                Item endDataItem3 = new Item();
                endDataItem3.setContent("3、不要久坐，每天适当的运动或做做家务，保持轻松愉悦的心情。");

                Item endDataItem5 = new Item();
                endDataItem5.setContent("4、减少吸烟或戒烟：");
                Item endDataItem6 = new Item();
                Template end3 = new Template();
                //jy
                end3.setSubType("jy");
                end3.setTmpType(tmpType);
                List<Template> endTemplates3 = templateMapper.selectByTemplate(end3);
                Template endTemplate3 = this.getIndex(endTemplates3);
                if(endTemplate3 != null){
                    endDataItem6.setContent(endTemplate3.getContent());
                }
                endDataItem6.setPrompt("");

                Item endDataItem7 = new Item();
                endDataItem7.setContent("5、减少饮酒或戒酒：");
                Item endDataItem8 = new Item();
                Template end4 = new Template();
                //gscdjq
                end4.setSubType("jj");
                end4.setTmpType(tmpType);
                List<Template> endTemplates4 = templateMapper.selectByTemplate(end4);
                Template endTemplate4 = this.getIndex(endTemplates4);
                if(endTemplate4 != null){
                    endDataItem8.setContent(endTemplate4.getContent());
                }
                endDataItem8.setPrompt("");

                Item endDataItem9 = new Item();
                endDataItem9.setContent("6、避免便秘情况，改善肠道菌群。");
                Item endDataItem10 = new Item();
                Template end5 = new Template();
                //gscdjq
                end5.setSubType("gscdjq");
                end5.setTmpType(tmpType);
                List<Template> endTemplates5 = templateMapper.selectByTemplate(end5);
                Template endTemplate5 = this.getIndex(endTemplates5);
                if(endTemplate5 != null){
                    endDataItem10.setContent(endTemplate5.getContent());
                }
                endDataItem10.setPrompt("");

                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                endList1.add(endDataItem1);
                endList1.add(endDataItem2);
                endList2.add(endDataItem3);
                endList3.add(endDataItem5);
                endList3.add(endDataItem6);
                endList4.add(endDataItem7);
                endList4.add(endDataItem8);
                endList5.add(endDataItem9);
                endList5.add(endDataItem10);
                endList.add(endList1);
                endList.add(endList2);
                endList.add(endList3);
                endList.add(endList4);
                endList.add(endList5);
            }else if (StrUtil.equals("GXT",tmpType)) {
                Item endDataItem1 = new Item();
                endDataItem1.setContent("2、保持健康体重及腰围：");
                Item endDataItem2 = new Item();
                //bctzjyw
                Template end1 = new Template();
                end1.setSubType("bctzjyw");
                end1.setTmpType(tmpType);
                List<Template> endTemplates1 = templateMapper.selectByTemplate(end1);
                Template endTemplate1 = this.getIndex(endTemplates1);
                if(endTemplate1 != null){
                    endDataItem2.setContent(endTemplate1.getContent());
                }
                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                Item endDataItem3 = new Item();
                endDataItem3.setContent("3、保证充足睡眠：");
                Item endDataItem4 = new Item();
                Template end2 = new Template();
                end2.setSubType("bzczsm");
                end2.setTmpType(tmpType);
                List<Template> endTemplates2 = templateMapper.selectByTemplate(end2);
                Template endTemplate2 = this.getIndex(endTemplates2);
                if(endTemplate2 != null){
                    endDataItem4.setContent(endTemplate2.getContent());
                }
                endDataItem4.setPrompt("");

                Item endDataItem5 = new Item();
                endDataItem5.setContent("4、常运动，勿久坐，保持轻松愉悦的心情。");
                Item endDataItem6 = new Item();
                Template end3 = new Template();
                //jy
                end3.setSubType("cydwjz");
                end3.setTmpType(tmpType);
                List<Template> endTemplates3 = templateMapper.selectByTemplate(end3);
                Template endTemplate3 = this.getIndex(endTemplates3);
                if(endTemplate3 != null){
                    endDataItem6.setContent(endTemplate3.getContent());
                }
                endDataItem6.setPrompt("");

                Item endDataItem7 = new Item();
                endDataItem7.setContent("5、戒烟限酒，避免空腹饮酒。");
                Item endDataItem8 = new Item();
                Template end4 = new Template();
                end4.setSubType("jyjj");
                end4.setTmpType(tmpType);
                List<Template> endTemplates4 = templateMapper.selectByTemplate(end4);
                Template endTemplate4 = this.getIndex(endTemplates4);
                if(endTemplate4 != null){
                    endDataItem8.setContent(endTemplate4.getContent());
                }
                endDataItem8.setPrompt("");

                Item endDataItem9 = new Item();
                endDataItem9.setContent("6、避免便秘情况，改善肠道菌群。");
                Item endDataItem10 = new Item();
                Template end5 = new Template();
                //gscdjq
                end5.setSubType("gscdjq");
                end5.setTmpType(tmpType);
                List<Template> endTemplates5 = templateMapper.selectByTemplate(end5);
                Template endTemplate5 = this.getIndex(endTemplates5);
                if(endTemplate5 != null){
                    endDataItem10.setContent(endTemplate5.getContent());
                }
                endDataItem10.setPrompt("");

                //TODO 这里是不是需要AI
                endDataItem2.setPrompt("");
                endList1.add(endDataItem1);
                endList1.add(endDataItem2);
                endList2.add(endDataItem3);
                endList3.add(endDataItem5);
                endList3.add(endDataItem6);
                endList4.add(endDataItem7);
                endList4.add(endDataItem8);
                endList5.add(endDataItem9);
                endList5.add(endDataItem10);
                endList.add(endList1);
                endList.add(endList2);
                endList.add(endList3);
                endList.add(endList4);
                endList.add(endList5);
            }
            endData.setScheme3(endList);
            //-------------最后一层完成---------------

            TemplateData templateData = new TemplateData();
            List templateList = new ArrayList();
            templateList.add(firstData);
            templateList.add(secondData);
            templateList.add(thirdData);
            templateList.add(fourthData);
            templateList.add(fifthData);
            templateList.add(endData);
            templateData.setPages(templateList);

            //最后的是一个json
            JSONObject lastJson = new JSONObject();
            lastJson.put(tmpType,templateData);
            templateVo.setPageMessage(lastJson);
        }
        return templateVo;
    }

    private Template getIndex(List<Template> templates) {
        if(ArrayUtil.isNotEmpty(templates) && templates.size() > 0){
            Random random = new Random();
            int index = random.nextInt(templates.size());
            return templates.get(index);
        }
        return null;
    }
}
