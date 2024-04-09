package com.dcm.thread.alg.d12;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2024/1/28
 * @description : 两数之和
 **/
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> keyMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (keyMap.containsKey(nums[i])) {
                result[0] = i;
                result[1] = keyMap.get(nums[i]);
                return result;
            }

            keyMap.put(target - nums[i], i);
        }

        return result;
    }
}
