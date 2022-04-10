package com.dcm.thread.alg.d5.q2;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 */
class Solution {

    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            sum = Math.max(curSum, sum);
        }
        return sum;
    }


    public int maxSubArray2(int[] nums) {
        int sum = 0;
        int ori = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            sum = Math.max(sum, ori);
        }
        return sum;
    }


    public int maxSubArray3(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
        }
        //取最大值
        return 0;
    }
}