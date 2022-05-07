package com.dcm.thread.alg.d10.q3;

class Solution {

    public static int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return ans;
    }


    public static int mySqrt1(int x) {
        // 二分搜索
        int left = 0, right = x;
        while(left < right){
            int mid = left + right >> 1;
            if((long) mid * mid < x){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return (long)right * right <= x ? right : right - 1;
    }


    public static void main(String[] args) {
        System.out.println(mySqrt(4));
    }
}