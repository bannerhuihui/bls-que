package com.bls.que.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
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
            List secondList = new ArrayList();
            List<String> secondList1 = new ArrayList<>();
            secondList1.add("1 复合多种营养素片");
            secondList1.add("每天2次，每次1片");
            secondList1.add("早餐/晚餐后温水吞服");
            List<String> secondList2 = new ArrayList<>();
            secondList2.add("2 高纤果蔬精华");
            secondList2.add("每天1次，每次1袋");
            secondList2.add("每日午餐前");
            List<String> secondList3 = new ArrayList<>();
            secondList3.add("3 灵芝富硒多肽蛋白粉");
            secondList3.add("每天1次，每次1袋");
            secondList3.add("每日早餐后");
            secondList.add(secondList1);
            secondList.add(secondList2);
            secondList.add(secondList3);
            secondData.setTable(secondList);
            //-------------第二层结束--------------

            //-------------第三层开始--------------
            ThirdData thirdData = new ThirdData();
            List thirdList = new ArrayList();
            List<Item> thirdList1 = new ArrayList<>();
            List<Item> thirdList2 = new ArrayList<>();
            List<Item> thirdList3 = new ArrayList<>();
            Item thirdItem1 = new Item();
            //根据temType和subType来查找（gdnr2）
            Template third1 = new Template();
            third1.setTmpType(tmpType);
            thirdItem1.setContent(" ");
            third1.setSubType("gdnr2");
            List<Template> thirdTemplates1 = templateMapper.selectByTemplate(third1);
            Template thirdTemplate1 = getIndex(thirdTemplates1);
            if(thirdTemplate1 != null){
                thirdItem1.setContent(thirdTemplate1.getContent());
            }
            //thirdItem1.setContent("免疫力低下会有哪些症状表现呢？最明显的就是身体抵抗力差，容易反复感冒且需要较长时间才能恢复；易过敏，甚至出现过敏性疾病，例如湿疹、荨麻疹、哮喘等。其次，精神状态不佳，常常感到精力不够、疲劳乏力、体虚多汗、失眠、营养不良等。免疫力低下人群不仅呼吸系统疾病频发，例如呼吸道及肺部炎症、过敏性鼻炎等，也会导致消化系统不适，容易出现胃肠道感染、便秘、腹泻、消化不良、食欲不振等症状。相较于正常人而言，免疫力过低者的伤口愈合时间会更长，特别是术后人群，这是由于免疫失守后病菌容易侵入人体，破坏可以让伤口愈合的血小板，导致伤口不易愈合，易感染，身体恢复慢。若长期处于免疫力不足的状态下，免疫细胞无法杀灭和抑制体内的致病菌，致病菌繁殖就会加快，导致疾病快速发展。");
            //TODO 这里是不是需要AI
            thirdItem1.setPrompt("");
            thirdList1.add(thirdItem1);
            Item thirdItem2 = new Item();
            Template third2 = new Template();
            third2.setTmpType(tmpType);
            thirdItem2.setContent(" ");
            if(StrUtil.equals("CD",tmpType)){
                third2.setSubType("cdwl");
            }else if (StrUtil.equals("GXT",tmpType)){
                third2.setSubType("tnb");
            }else if (StrUtil.equals("GXY",tmpType)){
                third2.setSubType("gxy");
            }else if (StrUtil.equals("GXZ",tmpType)){
                third2.setSubType("gxz");
            }else if (StrUtil.equals("ZHMY",tmpType)){
                third2.setSubType("myldx");
            }
            List<Template> thirdTemplates2 = templateMapper.selectByTemplate(third2);
            Template thirdTemplate2 = getIndex(thirdTemplates2);
            if(thirdTemplate2 != null){
                thirdItem2.setContent(thirdTemplate2.getContent());
            }
            //thirdItem2.setContent("综上可见，免疫力低下具有慢性、持续性、易反复的特点，那么，是什么原因导致免疫力低下呢？我们需要先了解它发生的原因，才能更好地应对和调理。一方面是由病理性因素引起，多种慢性病会引起免疫功能低下，例如高血压、糖尿病、肾病、自身免疫性疾病或其他系统性疾病，同时免疫力低下也会加大慢性病发生的几率。另一方面与生理因素关系密切，包括年龄、饮食习惯、生活方式、心理因素。随着年龄的增大，身体的各个器官功能出现退化，免疫力开始不断下降，但是饮食结构不合理是首要因素，长期不规律饮食，经常食用辛辣刺激性食物，会对身体造成一定的刺激，从而导致免疫力下降。其次，经常熬夜、睡眠不足、缺乏运动、过度劳累、精神压力大，身体的免疫系统受到抑制，也会导致免疫力下降。所以，掌握了免疫力低下的原因，在日常中加以注意，预防和调理就很轻松啦！");
            //TODO 这里是不是需要AI
            thirdItem2.setPrompt("");
            thirdList2.add(thirdItem2);
            Item thirdItem3 = new Item();
            Template third3 = new Template();
            thirdItem3.setContent(" ");
            third3.setTmpType(tmpType);
            if(StrUtil.equals("CD",tmpType)){
                third3.setSubType("cdwl");
            }else if (StrUtil.equals("GXT",tmpType)){
                third3.setSubType("tnbyy");
            }else if (StrUtil.equals("GXY",tmpType)){
                third3.setSubType("gxyyy");
            }else if (StrUtil.equals("GXZ",tmpType)){
                third3.setSubType("gxzyy");
            }else if (StrUtil.equals("ZHMY",tmpType)){
                third3.setSubType("myldxyy");
            }
            List<Template> thirdTemplates3 = templateMapper.selectByTemplate(third3);
            Template thirdTemplate3 = getIndex(thirdTemplates3);
            if(thirdTemplate3 != null){
                thirdItem3.setContent(thirdTemplate3.getContent());
            }
            //thirdItem3.setContent("接下来我们从营养调理的角度出发，通过五个维度对生理性因素导致的免疫力低下情况制定健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。饮食结构是直接影响免疫健康的因素，合理的食疗和营养素补充可有效地补充免疫力不足。");
            //TODO 这里是不是需要AI
            thirdItem3.setPrompt("");
            thirdList3.add(thirdItem3);
            thirdList.add(thirdList1);
            thirdList.add(thirdList2);
            thirdList.add(thirdList3);
            thirdData.setParagraph(thirdList);
            Item thirdItem4 = new Item();
            //固定内容3
            Template third4 = new Template();
            third4.setTmpType(tmpType);
            thirdItem4.setContent(" ");
            third4.setSubType("gdnr3");
            List<Template> thirdTemplates4 = templateMapper.selectByTemplate(third4);
            Template thirdTemplate4 = getIndex(thirdTemplates4);
            if(thirdTemplate4 != null){
                thirdItem4.setContent(thirdTemplate4.getContent());
            }
            //thirdItem4.setContent("一般免疫力低下可能会与机体内免疫细胞较少、免疫器官动力不足有关，导致免疫功能无法正常运行。饮食上保持营养均衡、食物多样，尽量选择优质高蛋白、富含维生素、矿物质和膳食纤维的食物，才能达到促进健康的目的。");
            //TODO 这里是不是需要AI
            thirdItem4.setPrompt("");
            thirdData.setScheme1(thirdItem4);
            //-------------第三层结束--------------

            //-------------第四层开始--------------
            FourthData fourthData = new FourthData();
            Item fourthItem = new Item();
            fourthItem.setPrompt("优化内容，并重新以列表形式输出");
            //饮食建议方案
            Template fourth = new Template();
            fourth.setTmpType(tmpType);
            fourthItem.setContent(" ");
            fourth.setSubType("ysjyfa");
            List<Template> fourthTemplates = templateMapper.selectByTemplate(fourth);
            Template fourthTemplate = getIndex(fourthTemplates);
            if(fourthTemplate != null){
                fourthItem.setContent(fourthTemplate.getContent());
            }
            //fourthItem.setContent("1. 一日三餐，定时定量。\\n2. 多在家吃饭，少在外就餐或点外卖。\\n3. 食物种类多样化，每周至少25种，谷类供能比占50%-60%。\\n4. 每天摄入谷薯类250-400克（生重），粗细搭配，即：全谷类（稻米、小麦、玉米、燕麦、黑米、小米、高粱等）、杂豆类（除黄豆、黑豆、青豆以外的豆类，包括赤豆、芸豆、绿豆、豌豆等）、薯类（红薯、土豆、山药、芋头等）。\\n5. 注意荤素搭配，多吃一些牛肉、鱼、鸡蛋等含有优质蛋白质的食物，可以一定程度上为制造免疫细胞提供基础。少吃肥肉、烟熏和腌制肉及加工肉制品。\\n6. 每天摄入300克牛奶或相当量的奶制品，乳糖不耐受者可以喝酸奶或低乳糖奶。不能用乳饮料代替奶制品。\\n7. 新鲜蔬菜顿顿有，蔬菜品种多样化，其中深色蔬菜占每餐蔬菜的一半。深色蔬菜是指深绿色（菠菜、油菜等）、红色或橘红色（西红柿、胡萝卜、南瓜等）、紫红色（紫洋葱、紫甘蓝、红苋菜等）蔬菜。\\n8. 新鲜水果天天吃，水果1-2种，200-350克。果汁饮料、果脯等不能代替鲜果。\\n9. 每周吃2-3次大豆和豆制品，如黄豆、豆腐、豆腐丝、豆浆等，每天摄入总量相当于25克大豆。\\n10. 适当摄入坚果，平均每天10克（可食部分重量）左右，大概是手握住一把的量，最好选原味。\\n11. 主动足量饮水，少量多次，每天1500-1700毫升为宜。首选温白开水，不要喝过凉的水。\\n12. 控制用盐量，清淡饮食，尽量少食或避免辛辣、油腻、油炸的食物，不要暴饮暴食。\\n13. 合理选择零食，零食量不影响正餐，晚上睡前60分钟最好不吃零食。新鲜水果、可生食蔬菜、奶制品（如牛奶、酸奶等）、蛋类、豆制品、原味坚果等可选做零食；含油、盐、糖较多的食物不适合当做零食，如糖果、含糖饮料、甜点、冷饮、果脯、薯条、薯片、油炸食品等。");
            fourthData.setScheme1(fourthItem);
            //-------------第四层结束--------------

            //-------------第五层开始--------------
            FifthData fifthData = new FifthData();
            Item fifthItem1 = new Item();
            //gdnr4
            fifthItem1.setContent("通过适当的运动锻炼可以促进身体的血液循环和新陈代谢，同时也有利于加速白细胞、淋巴细胞等免疫细胞的更新。长期的运动锻炼还可以改善身体的器官功能，增加心脏和肺部的耐受力，从而在一定程度上提高身体的免疫力。规律的体育运动可以使身体释放内啡肽和多巴胺等“快乐激素”，改善情绪，缓解焦虑和抑郁，同时帮助身体放松，改善睡眠质量。以下运动项目可以单独持续一项运动，也可以交叉运动，频率保证5次/周。");
            //TODO 这里是不是需要AI
            fifthItem1.setPrompt("");
            fifthData.setScheme2(fifthItem1);
            Item fifthItem2 = new Item();
            //ydjyfa
            fifthItem2.setContent("八段锦或者太极      30-40分钟    4-5次/周\\n\\n慢跑或者健步走      30分钟       3-4次/周\\n\\n广场舞或者健身操    30分钟       3-4次/周\\n\\n游泳                30-40分钟    2-3次/周\\n\\n羽毛球              30-40分钟    1-2次/周");
            //TODO 这里是不是需要AI
            fifthItem2.setPrompt("");
            fifthData.setScheme2YD(fifthItem2);
            Item fifthItem3 = new Item();
            //gdnr5
            fifthItem3.setContent("<p>1、 确保运动环境安全，场地要安全，平坦，以防意外。</p><p>2、 饮用充足水分，以防脱水。</p><p>3、 进行活动前要做热身，活动后要做整理、恢复运动。</p><p>4、 不要在饭后进行运动。</p><p>5、 运动强度随时调整，由轻到重，由少到多，中间适度休息。运动中若出现严重头晕、眼花等情况应立即停止运动。</p>");
            //TODO 这里是不是需要AI
            fifthItem3.setPrompt("");
            fifthData.setScheme2YDZY(fifthItem3);
            List<Item> fifthList = new ArrayList<>();
            Item fifthItem4 = new Item();
            //shjyfa1
            fifthItem4.setContent("1、保持健康体重及腰围：肥胖是造成慢性病的重要危险因素之一，通过减少热量摄入以及适量增加体育锻炼，把体重控制在健康状态。体重正常的BMI值是18.5-23.9kg/m²，");
            //TODO 这里是不是需要AI
            fifthItem4.setPrompt("");
            Item fifthItem5 = new Item();
            //shjyfa2
            fifthItem5.setContent("腰围在80厘米内，控制在这个范围有助于保持健康状态。");
            //TODO 这里是不是需要AI
            fifthItem5.setPrompt("");
            fifthList.add(fifthItem4);
            fifthList.add(fifthItem5);
            List<List<Item>> s3List = new ArrayList<>();
            s3List.add(fifthList);
            fifthData.setScheme3(s3List);
            //-------------第五层结束--------------

            //-------------最后一个层--------------
            EndData endData = new EndData();
            List endList = new ArrayList();
            List<Item> endListFirst = new ArrayList<>();
            List<Item> endListSecond = new ArrayList<>();
            Item endDataItem1 = new Item();
            //shjyfa3
            endDataItem1.setContent("2、坚持健康的生活习惯：");
            Item endDataItem2 = new Item();
            //shjyfa4
            endDataItem2.setContent("免疫力低下往往与个人生活作息不合理有关，比如经常熬夜，睡眠不足；经常吃辛辣刺激和高脂、高热量，并且难以消化的食物；经常吸烟喝酒、不爱喝水，不爱运动等。所以，我们要保证充足睡眠，不要熬夜，让身体内分泌正常运转，提高抗病能力；常运动，勿久坐，让新陈代谢跑起来，也同时避免出现肥胖、高血压、高血脂、高血糖等情况；戒烟戒酒，如必须饮酒应限量。");
            //TODO 这里是不是需要AI
            endDataItem2.setPrompt("");
            endListFirst.add(endDataItem1);
            endListFirst.add(endDataItem2);
            endList.add(endListFirst);
            Item endDataItem3 = new Item();
            //shjyfa5
            endDataItem3.setContent("3、保持愉悦的心情，适当减轻工作量，避免过度劳累。");
            Item endDataItem4 = new Item();
            //shjyfa6
            endDataItem4.setContent("若长时间处于精神紧张、压抑、焦虑的状态，不仅会影响正常的睡眠质量，还会影响人体的内环境，从而使免疫力下降。当人体长时间处于高负荷状态时，会消耗大量的能量和营养，导致免疫系统所需的资源不足。此外，过度劳累还会产生炎症和氧化应激等应激反应，这些反应会损害免疫细胞的功能，降低其对抗病原体的能力。因此，日常生活中需要保持平稳的情绪，不要让机体长时间处在太疲劳、紧张、焦虑的状态下。");
            //TODO 这里是不是需要AI
            endDataItem4.setPrompt("");
            endListSecond.add(endDataItem3);
            endListSecond.add(endDataItem4);
            endList.add(endListSecond);
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
