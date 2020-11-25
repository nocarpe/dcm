package com.dcm.test;

import com.dcm.application.StartMain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : yaoximing
 * @date : 2020-11-20
 * @description :
 **/
@SpringBootTest(classes = StartMain.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MainTest {

    @Test
    void contextLoads() {
        System.out.println(1);
    }
}
