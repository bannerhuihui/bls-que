package com.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bls.que.QueApplication;
import com.bls.que.bean.SyncOrderFDBean;
import com.bls.que.mapper.HistoryMapper;
import com.bls.que.mapper.SysMapper;
import com.bls.que.pojo.History;
import com.bls.que.pojo.Sys;
import com.bls.que.service.HistoryService;
import com.bls.que.vo.template.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @projectName: bls-que
 * @package: com.bls.service
 * @className: UserServiceTest
 * @author: huihui
 * @description: TODO
 * @date: 2024/4/30 14:44
 * @version: 1.0
 */
@SpringBootTest(classes = QueApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private HistoryService historyService;

    @Test
    public void TestHttp(){
        String s = HttpUtil.get("https://www.baidu.com");
        Console.log(s);
    }

    @Test
    public void testJPG(){
        int width = 400;
        int height = 300;

        // 创建一个新的BufferedImage对象
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取Graphics2D对象
        Graphics2D g2d = bufferedImage.createGraphics();

        // 设置背景颜色并填充整个图片
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 画一个矩形和一个圆形
        g2d.setColor(Color.BLUE);
        g2d.fillRect(50, 50, 100, 100);
        g2d.setColor(Color.RED);
        g2d.fillOval(200, 50, 100, 100);

        // 设置字体
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Hello, World!", 100, 200);

        // 释放Graphics2D对象
        g2d.dispose();

        // 保存图片到文件
        try {
            File outputfile = new File("generated_image.png");
            ImageIO.write(bufferedImage, "png", outputfile);
            System.out.println("Image generated successfully: " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String ss = "{\"name\":\"minging\",\"familyDisease\":[\"糖尿病\"],\"metabolicDiseases\":[\"高血压\"],\"drinkWeek\":\"少量饮酒\",\"smoking\":\"不抽烟但会接触二手烟\",\"sleepOneDay\":\"低于6个小时/天\",\"sleepQuality\":\"多梦(一晚上都在做梦，且回想不起来梦过什么)\",\"work\":\"偶尔会有一段时间焦虑\",\"amount\":\"只有上下班走走路\",\"defecation\":[\"颜色非黄或黄褐色\",\"便秘\"],\"foodShort\":[\"经常喝含糖饮料或吃甜品等高糖食品\",\"喜欢吃肉类或加工肉制品(火腿、培根等)\"],\"otherDisease\":[\"虚弱、经常犯困\"],\"needs\":\"高血糖\"}";
    }


    @Test
    public void rematDB(){
        //-------------最后一个层--------------
        EndData endData = new EndData();
        List endList = new ArrayList();
        List<Item> endListFirst = new ArrayList<>();
        List<Item> endListSecond = new ArrayList<>();
        Item endDataItem1 = new Item();
        endDataItem1.setContent("2、坚持健康的生活习惯：");
        Item endDataItem2 = new Item();
        endDataItem2.setContent("免疫力低下往往与个人生活作息不合理有关，比如经常熬夜，睡眠不足；经常吃辛辣刺激和高脂、高热量，并且难以消化的食物；经常吸烟喝酒、不爱喝水，不爱运动等。所以，我们要保证充足睡眠，不要熬夜，让身体内分泌正常运转，提高抗病能力；常运动，勿久坐，让新陈代谢跑起来，也同时避免出现肥胖、高血压、高血脂、高血糖等情况；戒烟戒酒，如必须饮酒应限量。");
        //TODO 这里是不是需要AI
        endDataItem2.setPrompt("");
        endListFirst.add(endDataItem1);
        endListFirst.add(endDataItem2);
        endList.add(endListFirst);
        Item endDataItem3 = new Item();
        endDataItem3.setContent("3、保持愉悦的心情，适当减轻工作量，避免过度劳累。");
        Item endDataItem4 = new Item();
        endDataItem4.setContent("若长时间处于精神紧张、压抑、焦虑的状态，不仅会影响正常的睡眠质量，还会影响人体的内环境，从而使免疫力下降。当人体长时间处于高负荷状态时，会消耗大量的能量和营养，导致免疫系统所需的资源不足。此外，过度劳累还会产生炎症和氧化应激等应激反应，这些反应会损害免疫细胞的功能，降低其对抗病原体的能力。因此，日常生活中需要保持平稳的情绪，不要让机体长时间处在太疲劳、紧张、焦虑的状态下。");
        //TODO 这里是不是需要AI
        endDataItem4.setPrompt("");
        endListSecond.add(endDataItem3);
        endListSecond.add(endDataItem4);
        endList.add(endListSecond);
        endData.setScheme3(endList);
        //-------------最后一层完成---------------

        //-------------第五层开始--------------
        FifthData fifthData = new FifthData();
        Item fifthItem1 = new Item();
        fifthItem1.setContent("通过适当的运动锻炼可以促进身体的血液循环和新陈代谢，同时也有利于加速白细胞、淋巴细胞等免疫细胞的更新。长期的运动锻炼还可以改善身体的器官功能，增加心脏和肺部的耐受力，从而在一定程度上提高身体的免疫力。规律的体育运动可以使身体释放内啡肽和多巴胺等“快乐激素”，改善情绪，缓解焦虑和抑郁，同时帮助身体放松，改善睡眠质量。以下运动项目可以单独持续一项运动，也可以交叉运动，频率保证5次/周。");
        //TODO 这里是不是需要AI
        fifthItem1.setPrompt("");
        fifthData.setScheme2(fifthItem1);
        Item fifthItem2 = new Item();
        fifthItem2.setContent("八段锦或者太极      30-40分钟    4-5次/周\\n\\n慢跑或者健步走      30分钟       3-4次/周\\n\\n广场舞或者健身操    30分钟       3-4次/周\\n\\n游泳                30-40分钟    2-3次/周\\n\\n羽毛球              30-40分钟    1-2次/周");
        //TODO 这里是不是需要AI
        fifthItem2.setPrompt("");
        fifthData.setScheme2YD(fifthItem2);
        Item fifthItem3 = new Item();
        fifthItem3.setContent("<p>1、 确保运动环境安全，场地要安全，平坦，以防意外。</p><p>2、 饮用充足水分，以防脱水。</p><p>3、 进行活动前要做热身，活动后要做整理、恢复运动。</p><p>4、 不要在饭后进行运动。</p><p>5、 运动强度随时调整，由轻到重，由少到多，中间适度休息。运动中若出现严重头晕、眼花等情况应立即停止运动。</p>");
        //TODO 这里是不是需要AI
        fifthItem3.setPrompt("");
        fifthData.setScheme2YDZY(fifthItem3);
        List<Item> fifthList = new ArrayList<>();
        Item fifthItem4 = new Item();
        fifthItem4.setContent("1、保持健康体重及腰围：肥胖是造成慢性病的重要危险因素之一，通过减少热量摄入以及适量增加体育锻炼，把体重控制在健康状态。体重正常的BMI值是18.5-23.9kg/m²，");
        //TODO 这里是不是需要AI
        fifthItem4.setPrompt("");
        Item fifthItem5 = new Item();
        fifthItem5.setContent("腰围在80厘米内，控制在这个范围有助于保持健康状态。");
        //TODO 这里是不是需要AI
        fifthItem5.setPrompt("");
        fifthList.add(fifthItem4);
        fifthList.add(fifthItem5);
        List<List<Item>> s3List = new ArrayList<>();
        s3List.add(fifthList);
        fifthData.setScheme3(s3List);
        //-------------第五层结束--------------

        //-------------第四层开始--------------
        FourthData fourthData = new FourthData();
        Item fourthItem = new Item();
        fourthItem.setPrompt("优化内容，并重新以列表形式输出");
        fourthItem.setContent("1. 一日三餐，定时定量。\\n2. 多在家吃饭，少在外就餐或点外卖。\\n3. 食物种类多样化，每周至少25种，谷类供能比占50%-60%。\\n4. 每天摄入谷薯类250-400克（生重），粗细搭配，即：全谷类（稻米、小麦、玉米、燕麦、黑米、小米、高粱等）、杂豆类（除黄豆、黑豆、青豆以外的豆类，包括赤豆、芸豆、绿豆、豌豆等）、薯类（红薯、土豆、山药、芋头等）。\\n5. 注意荤素搭配，多吃一些牛肉、鱼、鸡蛋等含有优质蛋白质的食物，可以一定程度上为制造免疫细胞提供基础。少吃肥肉、烟熏和腌制肉及加工肉制品。\\n6. 每天摄入300克牛奶或相当量的奶制品，乳糖不耐受者可以喝酸奶或低乳糖奶。不能用乳饮料代替奶制品。\\n7. 新鲜蔬菜顿顿有，蔬菜品种多样化，其中深色蔬菜占每餐蔬菜的一半。深色蔬菜是指深绿色（菠菜、油菜等）、红色或橘红色（西红柿、胡萝卜、南瓜等）、紫红色（紫洋葱、紫甘蓝、红苋菜等）蔬菜。\\n8. 新鲜水果天天吃，水果1-2种，200-350克。果汁饮料、果脯等不能代替鲜果。\\n9. 每周吃2-3次大豆和豆制品，如黄豆、豆腐、豆腐丝、豆浆等，每天摄入总量相当于25克大豆。\\n10. 适当摄入坚果，平均每天10克（可食部分重量）左右，大概是手握住一把的量，最好选原味。\\n11. 主动足量饮水，少量多次，每天1500-1700毫升为宜。首选温白开水，不要喝过凉的水。\\n12. 控制用盐量，清淡饮食，尽量少食或避免辛辣、油腻、油炸的食物，不要暴饮暴食。\\n13. 合理选择零食，零食量不影响正餐，晚上睡前60分钟最好不吃零食。新鲜水果、可生食蔬菜、奶制品（如牛奶、酸奶等）、蛋类、豆制品、原味坚果等可选做零食；含油、盐、糖较多的食物不适合当做零食，如糖果、含糖饮料、甜点、冷饮、果脯、薯条、薯片、油炸食品等。");
        fourthData.setScheme1(fourthItem);
        //-------------第四层结束--------------

        //-------------第三层开始--------------
        ThirdData thirdData = new ThirdData();
        List thirdList = new ArrayList();
        List<Item> thirdList1 = new ArrayList<>();
        List<Item> thirdList2 = new ArrayList<>();
        List<Item> thirdList3 = new ArrayList<>();
        Item thirdItem1 = new Item();
        thirdItem1.setContent("免疫力低下会有哪些症状表现呢？最明显的就是身体抵抗力差，容易反复感冒且需要较长时间才能恢复；易过敏，甚至出现过敏性疾病，例如湿疹、荨麻疹、哮喘等。其次，精神状态不佳，常常感到精力不够、疲劳乏力、体虚多汗、失眠、营养不良等。免疫力低下人群不仅呼吸系统疾病频发，例如呼吸道及肺部炎症、过敏性鼻炎等，也会导致消化系统不适，容易出现胃肠道感染、便秘、腹泻、消化不良、食欲不振等症状。相较于正常人而言，免疫力过低者的伤口愈合时间会更长，特别是术后人群，这是由于免疫失守后病菌容易侵入人体，破坏可以让伤口愈合的血小板，导致伤口不易愈合，易感染，身体恢复慢。若长期处于免疫力不足的状态下，免疫细胞无法杀灭和抑制体内的致病菌，致病菌繁殖就会加快，导致疾病快速发展。");
        //TODO 这里是不是需要AI
        thirdItem1.setPrompt("");
        thirdList1.add(thirdItem1);
        Item thirdItem2 = new Item();
        thirdItem2.setContent("综上可见，免疫力低下具有慢性、持续性、易反复的特点，那么，是什么原因导致免疫力低下呢？我们需要先了解它发生的原因，才能更好地应对和调理。一方面是由病理性因素引起，多种慢性病会引起免疫功能低下，例如高血压、糖尿病、肾病、自身免疫性疾病或其他系统性疾病，同时免疫力低下也会加大慢性病发生的几率。另一方面与生理因素关系密切，包括年龄、饮食习惯、生活方式、心理因素。随着年龄的增大，身体的各个器官功能出现退化，免疫力开始不断下降，但是饮食结构不合理是首要因素，长期不规律饮食，经常食用辛辣刺激性食物，会对身体造成一定的刺激，从而导致免疫力下降。其次，经常熬夜、睡眠不足、缺乏运动、过度劳累、精神压力大，身体的免疫系统受到抑制，也会导致免疫力下降。所以，掌握了免疫力低下的原因，在日常中加以注意，预防和调理就很轻松啦！");
        //TODO 这里是不是需要AI
        thirdItem2.setPrompt("");
        thirdList2.add(thirdItem2);
        Item thirdItem3 = new Item();
        thirdItem3.setContent("接下来我们从营养调理的角度出发，通过五个维度对生理性因素导致的免疫力低下情况制定健康调理方案，即饮食、运动、睡眠、心理和营养素。睡眠和心理方案按照第一页的内容就可以，这里就不赘述了。饮食结构是直接影响免疫健康的因素，合理的食疗和营养素补充可有效地补充免疫力不足。");
        //TODO 这里是不是需要AI
        thirdItem3.setPrompt("");
        thirdList3.add(thirdItem3);
        thirdList.add(thirdList1);
        thirdList.add(thirdList2);
        thirdList.add(thirdList3);
        thirdData.setParagraph(thirdList);
        Item thirdItem4 = new Item();
        thirdItem4.setContent("一般免疫力低下可能会与机体内免疫细胞较少、免疫器官动力不足有关，导致免疫功能无法正常运行。饮食上保持营养均衡、食物多样，尽量选择优质高蛋白、富含维生素、矿物质和膳食纤维的食物，才能达到促进健康的目的。");
        //TODO 这里是不是需要AI
        thirdItem4.setPrompt("");
        thirdData.setScheme1(thirdItem4);
        //-------------第三层结束--------------

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
        //secondData.setTable(secondList);
        //-------------第二层结束--------------


        //-------------第一层开始--------------
        FirstData firstData = new FirstData();
        List firstList = new ArrayList();
        FirstFirstData ffData1 = new FirstFirstData();
        ffData1.setTitle("饮食方案");
        List<Item> firstFFList1 = new ArrayList<>();
        Item firstItem1 = new Item();
        firstItem1.setContent("注意饮食多样化，多摄入优质高蛋白，多吃含维生素的食物，比如海鲜、瘦肉、牛奶、蔬菜水果等。");
        //TODO 这里是不是需要AI
        firstItem1.setPrompt("");
        Item firstItem2 = new Item();
        firstItem2.setContent("适当补充营养素修复身体机能。具体的饮食定制方案将由专业的营养师为您服务。");
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
        firstItem3.setContent("根据自己的体能和爱好，选择适合自己的运动方式，如慢跑、游泳、健身操等，不仅能够提高身体素质和自身免疫力，锻炼心肺功能，还有利于身体机能的恢复。");
        //TODO 这里是不是需要AI
        firstItem3.setPrompt("");
        firstFFList2.add(firstItem3);
        ffData2.setParagraph(firstFFList2);
        firstList.add(ffData2);

        FirstFirstData ffData3 = new FirstFirstData();
        ffData3.setTitle("睡眠方案");
        List<Item> firstFFList3 = new ArrayList<>();
        Item firstItem4 = new Item();
        firstItem4.setContent("大脑是全身各器官和免疫细胞的中枢，充足的睡眠有助于大脑定期分泌激素，调节各器官的功能和免疫细胞的活化。每天保证充足的睡眠时间和良好的睡眠质量，早睡早起，较好的睡眠时间是晚上10点前，成人的睡眠时长是7-8小时。");
        //TODO 这里是不是需要AI
        firstItem4.setPrompt("");
        firstFFList3.add(firstItem4);
        ffData3.setParagraph(firstFFList3);
        firstList.add(ffData3);

        FirstFirstData ffData4 = new FirstFirstData();
        ffData4.setTitle("心理方案");
        List<Item> firstFFList4 = new ArrayList<>();
        Item firstItem5 = new Item();
        firstItem5.setContent("心理压力过大容易引起代谢紊乱，导致免疫力降低。保持健康乐观的心态，避免紧张劳累，避免过度焦虑。平时要适当放松心情，每天进行户外活动、增加日光照射，如散步，多亲近大自然。多和朋友沟通聊天，有助疏散心情。");
        //TODO 这里是不是需要AI
        firstItem5.setPrompt("");
        firstFFList4.add(firstItem5);
        ffData4.setParagraph(firstFFList4);
        firstList.add(ffData4);

        firstData.setSchemes(firstList);
        //-------------第一层结束--------------

        TemplateData templateData = new TemplateData();
        List templateList = new ArrayList();
        templateList.add(firstData);
        templateList.add(secondData);
        templateList.add(thirdData);
        templateList.add(fourthData);
        templateList.add(fifthData);
        templateList.add(endData);
        templateData.setPages(templateList);
        System.out.println(JSONObject.toJSONString(templateData));
    }


    @Test
    public void randomTest(){

        String str = "注意饮食多样化，多摄入优质高蛋白，多吃含维生素的食物，比如海鲜、瘦肉、牛奶、蔬菜水果等。ABC";
        System.out.println(str.substring(0,44));
        System.out.println(str.substring(44));

    }


    @Test
    public void textJson(){
        String label = "";
        String text = "{\"name\":\"袁辉\",\"familyDisease\":[\"脑卒中\"],\"metabolicDiseases\":[\"无\"],\"drinkWeek\":\"少量饮酒\",\"smoking\":\"不抽烟但会接触二手烟\",\"sleepOneDay\":\"低于6个小时/天\",\"sleepQuality\":\"容易醒\",\"work\":\"偶尔会有一段时间焦虑\",\"amount\":\"几乎不动，久坐，下班开车\",\"defecation\":[\"颜色非黄或黄褐色\",\"便秘\"],\"foodShort\":[\"经常喝含糖饮料或吃甜品等高糖食品\"],\"otherDisease\":[\"虚弱、经常犯困\"],\"needs\":\"肠道健康\"}";
        JSONObject parse = JSONObject.parseObject(text);
        if(parse != null && parse.get("defecation") != null){
            System.out.println(parse.get("defecation").getClass());
            JSONArray jsonArray = (JSONArray) parse.get("defecation");
            for (Object key : jsonArray) {
                String strKey = (String) key;
                if(StrUtil.equals(strKey,"便秘")){
                    label = "便秘";
                } else if (StrUtil.equals(strKey,"经常腹泻")) {
                    label = "腹泻";
                }
            }
        }
        System.out.println(label);
    }


    @Test
    public void testArray(){

        //String arrStr = "[[\"1 复合多种营养素片\",\"每天2次，每次1片\",\"早餐/晚餐后温水吞服\"],[\"2 高纤果蔬精华\",\"每天1次，每次1袋\",\"每日午餐前\"],[\"3 灵芝富硒多肽蛋白粉\",\"每天1次，每次1袋\",\"每日早餐后\"]]";
        List secondList = new ArrayList();
        List<String> secondList1 = new ArrayList<>();
        secondList1.add("1 复合营养双蛋白固体饮料（麦香味）/（榛巧味）");
        secondList1.add("每天1次，每次1勺");
        secondList1.add("每日晚餐前");
        List<String> secondList2 = new ArrayList<>();
        secondList2.add("2 高纤果蔬精华");
        secondList2.add("每天1次，每次1袋");
        secondList2.add("每日午餐前");
        List<String> secondList3 = new ArrayList<>();
        secondList3.add("3 复合多种营养素片");
        secondList3.add("每天2次，每次1片");
        secondList3.add("早餐/晚餐后温水吞服");
        List<String> secondList4 = new ArrayList<>();
        secondList4.add("4 纳豆红曲地龙蛋白片");
        secondList4.add("每天2次，每次2粒");
        secondList4.add("早餐/晚餐后温水吞服");
        secondList.add(secondList1);
        secondList.add(secondList3);
        secondList.add(secondList2);
        secondList.add(secondList4);
        System.out.println(JSONObject.toJSONString(secondList));

    }

    private static final String SECRET = "your-256-bit-secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier verifier = JWT.require(algorithm).build();
    private static Map<String, String> tokenStore = new HashMap<>();

    // Method to generate a token
    public String getToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .sign(algorithm);
        tokenStore.put(username, token);
        return token;
    }

    // Method to verify a token
    public String verifyToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getSubject();
            return username;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }


    @Test
    public void testToken (){
        String userName = "admin";
        String token = getToken(userName);
        Console.log("token = > ",token);

        String s = verifyToken(token);
        System.out.println(s);

        String s1 = verifyToken(token + "1");
        System.out.println(StrUtil.isEmpty(s1));
    }

    @Autowired
    private SysMapper sysMapper;

    @Test
    public void testFd(){
        SyncOrderFDBean syncOrderFDBean = new SyncOrderFDBean();
        //historyService.syncOrderToFD(38);
        Sys sys = sysMapper.selectByName("高血脂2980");
        JSONArray goodsList = null;
        if(sys != null){
            goodsList = JSONArray.parseArray(sys.getMessage());
        }
        System.out.println();
        syncOrderFDBean.setGoods(goodsList);
        System.out.println("我的编译"+JSONObject.toJSONString(syncOrderFDBean));
    }

    @Test
    public void insertGoods(){
        Sys sys = new Sys();
        sys.setName("高血脂2980");
        JSONArray js = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("gid",1685);
        obj1.put("qty",1);
        JSONObject obj2 = new JSONObject();
        obj2.put("gid",1686);
        obj2.put("qty",1);
        JSONObject obj3 = new JSONObject();
        obj3.put("gid",1687);
        obj3.put("qty",1);
        JSONObject obj4 = new JSONObject();
        obj4.put("gid",1688);
        obj4.put("qty",1);
        JSONObject obj5 = new JSONObject();
        obj5.put("gid",1689);
        obj5.put("qty",1);
        js.add(obj1);
        js.add(obj2);
        js.add(obj3);
        js.add(obj4);
        js.add(obj5);
        sys.setMessage(JSONObject.toJSONString(js));
        sysMapper.insertSelective(sys);
    }

    @Test
    public void testSync(){
        historyService.syncOrderToFD(67);
    }

}
