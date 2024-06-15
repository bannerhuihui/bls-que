package com.bls.que;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: bls-que
 * @package: com.bls.que
 * @className: QueApplication
 * @author: huihui
 * @description: 项目入口
 * @date: 2024/4/30 11:01
 * @version: 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "com.bls.que.mapper")
public class QueApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueApplication.class);
    }
}
