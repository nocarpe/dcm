package com.dcm.thread.alg.d27;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : yyyao
 * @date : 2025/2/12
 * @description :
 **/
public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int count = len - k + 1;
        int[] result = new int[count];

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }

        result[0] = nums[queue.peekFirst()];
        for (int j = k; j < len; j++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[j]) {
                queue.pollLast();
            }
            queue.addLast(j);

            if (queue.peekFirst() < j - k + 1) {
                queue.pollFirst();
            }
            result[j - k + 1] = nums[queue.peekFirst()];
        }

        return result;

    }

}
