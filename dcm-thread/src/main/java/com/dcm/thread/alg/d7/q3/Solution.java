package com.dcm.thread.alg.d7.q3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，，计算按此排列的柱子下雨之后能接多少雨水。
 */
class Solution {


    //双指针
    public int trap(int[] height) {
        int len = height.length - 1;
        int left = 0;
        int right = len;
        int left_max = 0;
        int right_max = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (left_max < height[left]) {
                    left_max = height[left];
                } else {
                    res += left_max - height[left];
                }
                left++;
            } else {
                if (height[right] > right_max) {
                    right_max = height[right];
                } else {
                    res += right_max - height[right];
                }
                right--;
            }
        }
        return res;

    }

    /**
     * 栈 单调递减栈
     */
    public int trap2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int temp_height = Math.min(height[i], height[stack.peek()]) - height[top];
                res += distance * temp_height;
            }
            stack.push(i);
        }
        return res;
    }


    public int trap3(int[] height) {
        int len = height.length - 1;
        int ans = 0;
        int left = 0;
        int leftMax = height[0];
        int rightMax = height[len];
        int right = len;
        while (left < right) {
            if (leftMax < rightMax) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    ans += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    ans += rightMax - height[right];
                }
                right--;
            }
        }
        return ans;
    }
}