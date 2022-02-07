package com.dcm.thread.alg.d2.q1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : yaoximing
 * @date : 2022/2/2
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 执行用时：
 * 82 ms
 * , 在所有 Java 提交中击败了
 * 11.16%
 * 的用户
 * 内存消耗：
 * 55.5 MB
 * , 在所有 Java 提交中击败了
 * 31.20%
 * 的用户
 **/
public class SolutionA {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int count = length - k + 1;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] result = new int[count];
        result[0] = queue.peek()[0];
        for (int j = k; j < length; j++) {
            queue.offer(new int[]{nums[j], j});
            while (queue.peek()[1] < j - k + 1) {
                queue.poll();
            }
            result[j - k + 1] = queue.peek()[0];
        }

        return result;
    }


    public static int[] maxSlidingWindowB(int[] nums, int k) {
        int length = nums.length;
        int count = length - k + 1;
        int[] result = new int[count];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        result[0] = nums[deque.peekFirst()];
        for (int j = k; j < length; j++) {
            while (!deque.isEmpty() && nums[j] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            while (deque.peekFirst() < j - k + 1) {
                deque.pollFirst();
            }
            result[j - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 9361, -750, -660, -1, 3, 33, 32, 321};
        int[] arr = maxSlidingWindow(nums, 2);
        System.out.println(Arrays.toString(arr));
    }

}
