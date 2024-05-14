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




}
