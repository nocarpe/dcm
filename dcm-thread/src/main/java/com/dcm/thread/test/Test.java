package com.dcm.thread.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-05-20
 * @description :
 **/
public class Test {
    public static final String TIME_FORMAT = "HH:mm";
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Float f = new Float(23.441f);

        //DES des = SecureUtil.des(Base64.getDecoder().decode("pHBGusspFeo="));
        double a = f;
       System.out.println(Double.valueOf(f.toString()));
    }
}
