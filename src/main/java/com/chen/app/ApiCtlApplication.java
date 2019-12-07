package com.chen.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chen.app.mapper")
public class ApiCtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCtlApplication.class, args);
    }

}
