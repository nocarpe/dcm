package com.dcm.thread.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : yaoximing
 * @date : 2021-09-14
 * @description :
 **/
public class Test {


    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();

        map.put(1,"a");
        map.put(2,"n");

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        a.add(1);
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);
        b.stream().collect(Collectors.groupingBy(obj->map.get(obj)));

    }
}
