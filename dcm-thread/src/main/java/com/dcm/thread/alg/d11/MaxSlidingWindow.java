package com.dcm.thread.alg.d11;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : yyyao
 * @date : 2023/10/17
 * @description :
 **/
public class MaxSlidingWindow {

    //滑动窗口的最大值

    /**
     * 给你一个整数数组nums,有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的K个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口的最大值。
     * nums=[1,3,-1,-3,5,3,6,7],k=3
     * [3,3,3,5,5,6,7]
     */

    public int[] maxSlidingWindow(int[] nums, int k) {

        int length = nums.length;
        int count = length - k + 1;
        int[] result = new int[count];
        if (length <= 1 || k < 2) {
            return nums;
        }
        for (int i = 0; i < count; i++) {
            int[] temp = new int[k];
            int j = 0;
            while (j < k) {
                temp[j] = nums[j + i];
                j++;
            }
            result[i] = getLargest(temp);
        }
        return result;
    }

    private int getLargest(int[] temp) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        int length = nums.length;
        int count = length - k + 1;
        int[] result = new int[count];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        result[0] = nums[deque.peekFirst()];

        for (int j = k; j < length; j++) {

            while (!deque.isEmpty() && nums[j] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(j);

            while (deque.peekFirst() < j - k + 1) {
                deque.pollFirst();
            }

            result[j - k + 1] = nums[deque.peekFirst()];
        }

        return result;

    }


    /**
     * 给你一个整数数组nums,有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的K个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口的最大值。
     * nums=[1,3,-1,-3,6,4,5,3,9],k=3
     * [3,3,3,5,5,6,7]
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int length = nums.length;
        int count = length - k + 1;
        int result[] = new int[count];
        if (length <= 1 || k < 2) {
            return result;
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
        }
        result[0] = nums[deque.peekFirst()];
        for (int j = k; j < length; j++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[j]) {
                deque.pollLast();
            }
            deque.addLast(j);

            while (deque.peekFirst() > j - k + 1) {
                deque.pollFirst();
            }

            result[j - k + 1] = nums[deque.peekFirst()];


        }

        return result;
    }


}



