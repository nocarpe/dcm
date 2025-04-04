package com.dcm.thread.alg.d22;

/**
 * @author : yyyao
 * @date : 2025/1/4
 * @description :
 **/
public class Solution {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        //分割线左边的所有元素需要满足的个数
        int totalLeft = (m + n + 1) / 2;
        //在nums1的区间[0,m]里查找恰当的分割线
        //使得nums1[i-1]<=nums2[j] && nums1[i]>=nums2[j-1]
        int left = 0, right = m;

        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;

            if (nums1[i - 1] > nums2[j]) {
                //下一个搜索的区间[left,i-1]
                right = i - 1;
            } else {
                //下一个搜索的区间[i,right]
                left = i;
            }
        }
        int i = left;
        int j = totalLeft - i;

        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];

        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == m ? Integer.MAX_VALUE : nums2[j];

        if (((m + n) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];

    }


}
