package com.dcm.application.oth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author : yaoximing
 * @date : 2021/6/23
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();

        System.out.println();
        int minute = localDateTime.getMinute();
        int hour = localDateTime.getHour();
        if (minute < 30) {
            startTime = startTime.withMinute(30).withSecond(0).withNano(0);
            endTime = endTime.withHour(hour + 1).withMinute(0).withSecond(0).withNano(0);
        } else {
            startTime = startTime.withHour(hour + 1).withMinute(0).withSecond(0).withNano(0);
            endTime = endTime.withHour(hour + 1).withMinute(30).withSecond(0).withNano(0);
        }
        System.out.println(startTime.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(endTime);

    }

}
