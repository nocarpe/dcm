package com.dcm.thread.alg.d6.q2;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
class Solution {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }


        }

        return res;
    }
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer,Integer> map =new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                res[0] = map.get(nums[i]);
                res[1] =i;

            }
            map.put(target-nums[i],i);
        }
        return res;
    }








    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */


    public int[] getTotal(int[] nums, int target){

        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(nums[i])){
                result[0]=i;
                result[1]=map.get(nums[i]);
                return result;
            }

            map.put(target-nums[i],i);
        }

        return result;
    }











}