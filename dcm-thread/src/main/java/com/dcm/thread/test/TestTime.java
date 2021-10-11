package com.dcm.thread.test;

import java.time.LocalDateTime;

/**
 * @author : yaoximing
 * @date : 2021/7/16
 * @description :
 **/
public class TestTime {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 7, 16, 23, 47, 00);
        LocalDateTime startTime = LocalDateTime.of(2021, 7, 16, 23, 37, 00);
        LocalDateTime endTime = LocalDateTime.of(2021, 7, 16, 23, 37, 00);
        int minute = localDateTime.getMinute();
        int hour = localDateTime.getHour();
        if (minute < 30) {
            startTime = startTime.withMinute(30).withSecond(0).withNano(0);
            endTime = endTime.withHour(hour + 1).withMinute(0).withSecond(0).withNano(0);
        } else {
            startTime = startTime.withHour(hour + 1).withMinute(0).withSecond(0).withNano(0);
            endTime = endTime.withHour(hour + 1).withMinute(30).withSecond(0).withNano(0);
        }
        System.out.println(startTime + "====" + endTime);


    }
}
