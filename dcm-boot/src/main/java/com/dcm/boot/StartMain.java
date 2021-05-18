package com.dcm.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yaoximing
 * @date : 2020-11-19
 * @description : 启动入口
 **/
@SpringBootApplication
@EnableAutoConfiguration
public class StartMain {

    public static void main(String[] args) {
        SpringApplication.run(StartMain.class, args);
    }
}
