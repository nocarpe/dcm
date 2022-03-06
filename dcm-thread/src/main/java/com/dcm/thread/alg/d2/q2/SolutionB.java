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
            return null;
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


    public static int getLongest(char[] array, int left, int right) {
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

    /**
     * 状态转移
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        int maxLen = 1;
        int beginIdx = 0;

        boolean[][] dp = new boolean[length][length];
        //对角线
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        //dp[i][j] 参考左下方 dp[i+1][j-1]
        char[] arr = s.toCharArray();
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    beginIdx = i;
                }

            }


        }
        // 4. 返回值
        return s.substring(beginIdx, beginIdx + maxLen);
    }


    public static void main(String[] args) {

        System.out.println(longestPalindrome2("aaabbbcccc"));

    }
}
