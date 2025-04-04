package com.dcm.thread.alg.d19;

import com.dcm.thread.alg.ListNode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author : yyyao
 * @date : 2024/12/7
 * @description :
 **/
public class SolutionRevert {


    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;

        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        int count = 0;
        ListNode check = head;
        ListNode cur = head;
        ListNode pre = null;
        int index = 0;
        ListNode next = null;
        while (count < k && check != null) {
            check = check.getNext();
            count++;
        }
        if (count == k) {
            while (cur.getNext() != null && index < k) {
                next = cur.getNext();
                cur.setNext(pre);
                pre = cur;
                cur = next;
                index++;
            }
            if (next != null) {
                head.setNext(reverseKGroup(next, k));
            }
            return pre;
        } else {
            return head;
        }


    }


    public int targetLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }


    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                res[0] = i;
                res[1] = map.get(numbers[i]);
                return res;
            }
            map.put(target - numbers[i], i);


        }
        return res;

    }


    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }


    public int maxSubArray(int[] nums) {

        int sum = nums[0];

        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            sum = Math.max(sum, curSum);
        }
        return sum;

    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //保持起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            //把回文看成中间的部分全是同一个字符，左右部分相对称
            //找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        //定位中间部分的最后一个字符
        int ans = high;
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        //记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + 1] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public int searchK(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] < target ? -1 : 0;
        }
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            } else {
                if (nums[mid] <= target && target < nums[len - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

        }

        return -1;

    }


    public void mergeArrays(int[] nums1, int m, int[] nums2, int n) {
        int tail = nums1.length - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p2 >= 0) {
            if (p1 < 0 || nums1[p1] <= nums2[p2]) {
                nums1[tail--] = nums2[p2--];
            } else {
                nums1[tail--] = nums2[p1--];
            }
        }
    }

    public void mergeTwoArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = --m + n--;
        while (n >= 0) {
            nums1[i--] = m > 0 && nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

    }


    public int[][] mergeArea(int[][] intervals){

        Arrays.sort(intervals,Comparator.comparingInt(o->o[0]));

        LinkedList<int[]> merged = new LinkedList<>();


        for(int[] interval:intervals){

            if(merged.isEmpty() || merged.getLast()[1]<interval[0]){
                merged.add(interval);
            }else {
                merged.getLast()[1] = Math.max(merged.getLast()[1],interval[1]);
            }

        }

        return merged.toArray(new int[merged.size()][]);
    }



}
