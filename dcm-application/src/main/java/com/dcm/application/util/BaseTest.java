package com.dcm.application.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2020-12-12
 * @description :
 **/


public class BaseTest {


    public static void main(String[] args) {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.HOUR_OF_DAY, 23);
        System.out.println(cl.get(Calendar.HOUR_OF_DAY));
        System.out.println(cl.get(Calendar.MINUTE));

        int part = cl.get(Calendar.HOUR_OF_DAY) * 2;
        if (cl.get(Calendar.MINUTE) > 30) {
            part += 1;
        }
        System.out.println(part);
        Bitmap bitmap = new Bitmap(4);
        for (int i = 0; i<5;i++) {
            bitmap.set(i,false);
        }
        System.out.println(bitmap.toString());
    }
}
