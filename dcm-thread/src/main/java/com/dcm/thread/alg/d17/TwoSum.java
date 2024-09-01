package com.dcm.thread.alg.d17;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2024/8/28
 * @description：两数之和 target 已知有x,y两个值
 **/
public class TwoSum {


    public int[] calTwoSum(int[] arr, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> tempMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (tempMap.containsKey(arr[i])) {
                res[0] = i;
                res[1] = tempMap.get(arr[i]);
                return res;
            }
            tempMap.put(target - arr[i], i);

        }

        return res;
    }



}
