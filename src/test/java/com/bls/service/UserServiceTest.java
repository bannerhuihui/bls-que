package com.bls.service;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import com.bls.que.mapper.UserMapper;
import com.bls.que.pojo.User;
import com.bls.que.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @projectName: bls-que
 * @package: com.bls.service
 * @className: UserServiceTest
 * @author: huihui
 * @description: TODO
 * @date: 2024/4/30 14:44
 * @version: 1.0
 */
@SpringBootTest
public class UserServiceTest {

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




}
