package com.dcm.thread.alg.d25;

/**
 * @author : yyyao
 * @date : 2025/1/18
 * @description :
 **/
public class Zifucuan {


    public int myAtoi(String s) {
        s =s.trim();
        if(s.length() ==0){
            return 0;
        }
        if(!Character.isDigit(s.charAt(0))&& s.charAt(0)!='-'&& s.charAt(0)!='+'){
            return 0;
        }
        int ans =0;
        boolean reg = s.charAt(0)=='-';
        int i = !Character.isDigit(s.charAt(0))?1:0;
        while (i<s.length() && Character.isDigit(s.charAt(i))){

            int temp = ((reg?Integer.MIN_VALUE:Integer.MIN_VALUE+1)+(s.charAt(i)-'0'))/10;
            if(temp>ans){
                return reg?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            ans = ans*10-(s.charAt(i++)-'0');
        }
        return reg?ans:-ans;

    }
    public int myAtoi2(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        int i = 0;
        int sign = 1; // 符号位，默认为正
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            sign = s.charAt(0) == '-' ? -1 : 1;
            i++;
        }
        int ans = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // 判断是否溢出
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
            i++;
        }
        return sign * ans;
    }


}
