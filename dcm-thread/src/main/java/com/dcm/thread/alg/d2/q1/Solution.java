package com.dcm.thread.alg.d2.q1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 超出时间限制
 */
class Solution {


    public static int[] maxSlidingWindow(int[] nums, int k) {

        int length = nums.length;
        int count = length - k +1;

        if (length <= 1 || k == 1) {
            return nums;
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            //构建单个window
            int[] temp = new int[k];
            int tempI = 0;
            while (tempI < k) {
                temp[tempI] = nums[tempI+i];
                tempI++;
            }
            result[i] = getLargest(temp);
        }

        return result;
    }


    /**
     * 单个window 最大
     */
    public static  int getLargest(int[] windows) {
        int num = Integer.MIN_VALUE;
        for (int i = 0; i < windows.length; i++) {
            if (num < windows[i]) {
                num = windows[i];
            }
        }

        return num;
    }

    public static int[] maxSlidingWindowA(int[] nums, int k) {
        int n = nums.length;
        Queue<int[]> pq = new PriorityQueue<>(
            (pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,9361,-750,-660,-1,3,33,32,321};
        int[] arr =maxSlidingWindowA(nums,2);
        System.out.println(Arrays.toString(arr));
    }

}