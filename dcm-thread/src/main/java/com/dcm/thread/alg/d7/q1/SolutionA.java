package com.dcm.thread.alg.d7.q1;

public class SolutionA {


    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        int add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int res = x + y + add;
            stringBuilder.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        stringBuilder.reverse();
        return stringBuilder.toString();

    }
}
