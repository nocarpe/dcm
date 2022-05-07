package com.dcm.thread.alg.d9.q5;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数
 *
 * 状态方程
 * word1[i] = word2[j],op[i][j] = op[i-1][j-1]
 * 否则 op[i][j]= 1+Min(op[i-1][j],op[i][j-1],op[i-1][j-1]);
 */
class Solution {


    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            for (int j = 0; j < len2 + 1; j++) {
                if (i == 0) {
                    dp[0][j] = j;
                } else if (j == 0) {
                    dp[i][0] = i;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j]));
                }
            }
        }

        return dp[len1][len2];
    }


    public static void main(String[] args) {
        String word1 = "asdada";
        String word2 = "ssdsadad";
        System.out.println(new Solution().minDistance(word1,word2));
    }

}