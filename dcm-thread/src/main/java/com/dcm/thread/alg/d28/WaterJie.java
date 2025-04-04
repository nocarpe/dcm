package com.dcm.thread.alg.d28;

import java.util.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author : yyyao
 * @date : 2025/3/2
 * @description :
 **/
public class WaterJie {


    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int temp_height = Math.min(height[i], height[stack.peek()] - height[top]);
                res += distance * temp_height;
            }
            stack.push(i);
        }
        return res;
    }


    public int jieyushui(int[] arr) {
        Deque<Integer> queue = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[i] > arr[queue.peek()]) {
                int top = queue.pop();
                if (queue.isEmpty()) {
                    continue;
                }
                int distance = i - queue.peek() + 1;
                int temp_height = Math.min(arr[i], arr[queue.peek()] - arr[top]);
                res += distance * temp_height;
            }

            queue.push(i);
            System.out.println(queue);
        }

        return res;
    }

    public int trarStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    continue;
                }
                int distance = i - stack.peek() - 1;
                int temph = Math.min(arr[i], arr[stack.peek()] - arr[top]);
                res += distance * temph;
            }
            stack.push(i);
        }
        return res;
    }


    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};

    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }

        }
        return ans;


    }

    //大于两边 峰值
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;


    }

    public int findlargest(char[] str, int low, int[] range) {
        int len = str.length;
        int high = low;
        while (high < len - 1 && str[high + 1] == str[low]) {
            high++;
        }
        int ans = high;
        while (low > 0 && high < len - 1 && str[high + 1] == str[low - 1]) {
            low--;
            high++;
        }
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }

        return ans;

    }


}
