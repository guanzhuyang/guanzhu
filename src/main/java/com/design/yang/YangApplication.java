package com.design.yang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.design.yang.mapper")
//@ServletComponentScan(basePackages = "com.design.yang.filter")
public class YangApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangApplication.class, args);
    }

}

