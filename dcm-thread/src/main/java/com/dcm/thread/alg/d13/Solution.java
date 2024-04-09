package com.dcm.thread.alg.d13;

/**
 * @author : yyyao
 * @date : 2024/2/29
 * @description :罗马文字
 **/
public class Solution {

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};


    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }

        }
        return roman.toString();
    }


    public String intToRoman1(int num) {
        StringBuffer s=new StringBuffer();
        while(num>=0){
            if(num>=1000){ num -= 1000; s.append("M"); }
            else if(num>=900){ num -= 900; s.append("CM"); }
            else if(num>=500){ num -= 500; s.append("D"); }
            else if(num>=400){ num -= 400; s.append("CD"); }
            else if(num>=100){ num -= 100; s.append("C"); }
            else if(num>=90){ num -= 90; s.append("XC"); }
            else if(num>=50){ num -= 50; s.append("L"); }
            else if(num>=40){ num -= 40; s.append("XL"); }
            else if(num>=10){ num -= 10; s.append("X"); }
            else if(num>=9){ num -= 9; s.append("IX"); }
            else if(num>=5){ num -= 5; s.append("V"); }
            else if(num>=4){ num -= 4; s.append("IV"); }
            else if(num>=1){ num -= 1; s.append("I"); }
        }
        return s.toString();
    }
}
