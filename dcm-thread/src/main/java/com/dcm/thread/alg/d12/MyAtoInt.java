package com.dcm.thread.alg.d12;

/**
 * @author : yyyao
 * @date : 2024/1/28
 * @description :字符串转换整数
 **/
public class MyAtoInt {

    public static void main(String[] args) {


        System.out.println(MyAtoi("435"));
    }



    public static int MyAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }

        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-' && s.charAt(0) != '+') {
            return 0;
        }
        int ans = 0;

        boolean neg = s.charAt(0) == '-';

        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;

        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int temp = ((neg ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (s.charAt(i) - '0')) / 10;

            if (temp > ans) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 - (s.charAt(i++) - '0');

        }
        return neg ? ans : -ans;
    }

}
