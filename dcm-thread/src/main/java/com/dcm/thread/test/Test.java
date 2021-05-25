package com.dcm.thread.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-05-20
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "v");
        String str = JSON.toJSONString(map);
        List<String> list =new ArrayList<>(null);
        list.add("1");
        System.out.println(list);
        Map<Integer, String> map1 = (Map<Integer, String>) JSON.parseObject(str, Map.class);
        System.out.println(map1);
    }
}
