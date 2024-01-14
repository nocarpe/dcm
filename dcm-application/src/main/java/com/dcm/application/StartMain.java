package com.dcm.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : yaoximing
 * @date : 2020-11-19
 * @description : 启动入口
 **/
@MapperScan(basePackages = "com.dcm.application.dal.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StartMain {

    public static void main(String[] args) {
        SpringApplication.run(StartMain.class, args);
    }
}
