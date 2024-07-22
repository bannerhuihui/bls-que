package com.test;

import com.bls.que.QueApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @projectName: bls-que
 * @package: com.bls.service
 * @className: UserServiceTest
 * @author: huihui
 * @description: TODO
 * @date: 2024/4/30 14:44
 * @version: 1.0
 */
public class UserServiceTest {

    @Test
    public void orderId(){
        // 生成时间戳部分，使用当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        // 生成随机数部分，使用随机数生成器生成6位随机数
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 生成6位随机数

        // 拼接时间戳和随机数生成订单号
        String orderNumber = timestamp + String.valueOf(randomNumber);
        System.out.println(orderNumber);
    }


}
