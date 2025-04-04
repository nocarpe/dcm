package com.dcm.thread.alg.d25;

import java.util.Arrays;

/**
 * @author : yyyao
 * @date : 2025/1/18
 * @description : 下一个排列
 **/
public class NextArr {

    public void nextPermutation(int[] nums) {
        int len = nums.length;

        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }


    public void nextPermutation2(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    if (nums[j] > nums[j - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }
}
