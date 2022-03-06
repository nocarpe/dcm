package com.dcm.thread.alg.d2.q2;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 **/
public class SolutionD {


    public static int lengthOfLongestSubstring2(String s) {
        int rk = 0, ans = 0;
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk < n && !occ.contains(s.charAt(rk))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk));
                ++rk;
            }

            ans = Math.max(ans, rk - i);


        }

        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }


    public static void main(String[] args) {

        String str = "zaavbsaa";
        System.out.println(lengthOfLongestSubstring3(str));
        System.out.println(lengthOfLongestSubstring2(str));
    }


    public static int lengthOfLongestSubstring3(String s) {
        int res = 0;
        int j = 0;
        Set<Character> set = new HashSet<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                set.remove(s.charAt(i - 1));
            }
            while (j < len && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
            }
            res = Math.max(res, j - i);
        }

        return res;
    }


}
