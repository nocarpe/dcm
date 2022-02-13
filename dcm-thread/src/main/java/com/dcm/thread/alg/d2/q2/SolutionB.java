package com.dcm.thread.alg.d2.q2;

/**
 * @description :给你一个字符串 s，找到 s 中最长的回文子串。
 **/
public class SolutionB {


    public static String longestPalindrome2(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int maxLen = 1;
        int begin = 0;
        if (len < 2) {
            return s;
        }
        for (int i = 0; i < len; i++) {
            int jishulen = getLongest(arr, i, i);
            int oushulen = getLongest(arr, i, i + 1);
            int curMaxLen = Math.max(jishulen, oushulen);
            if (curMaxLen > maxLen) {
                maxLen = curMaxLen;
                begin = i - (maxLen - 1) / 2;
            }
        }

        return s.substring(begin, begin + maxLen);
    }


    public  static int getLongest(char[] array, int left, int right) {
        int i = left;
        int j = right;
        int len = array.length;
        while (i >= 0 && j < len) {
            if (array[i] == array[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }

        return j - i - 1;
    }


    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }

        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        return null;
    }

    public static void main(String[] args) {

        System.out.println(longestPalindrome2("aaabbbcccc"));
    }
}
