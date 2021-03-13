package com.yy.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CoreApplication:
 *
 * @Author: YangYang
 * @Date: 2021/3/2 11:51
 */
@SpringBootApplication(scanBasePackages="com.yy")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
