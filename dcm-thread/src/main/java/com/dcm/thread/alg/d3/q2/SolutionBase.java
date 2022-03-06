package com.dcm.thread.alg.d3.q2;

/**
 * @description :
 **/
public class SolutionBase {

    public int[] insertSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;

        }

        return nums;
    }
}
