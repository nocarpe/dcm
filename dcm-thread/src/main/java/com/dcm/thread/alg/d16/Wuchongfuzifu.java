package com.dcm.thread.alg.d16;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2024/4/20
 * @description :
 **/
public class Wuchongfuzifu {


    public static int longestLen(String s) {
        int len = s.length();
        int result = 0;
        Map<Integer, Integer> charIndex = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            charIndex.put(i, -1);
        }
        int start = 0;
        for (int i = 0; i < len; i++) {
            int tempIndex = s.charAt(i);
            start = Math.max(start, charIndex.get(tempIndex) + 1);
            result = Math.max(result, i - start + 1);
            charIndex.put(tempIndex, i);
        }

        return result;

    }

    /**
     * 无重复数的最长子串
     */
    public static int longestLen1(String s) {
        int len = s.length();
        int result = 0;
        Map<Integer, Integer> charIdx = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            charIdx.put(i, -1);
        }
        int start = 0;
        for (int j = 0; j < len; j++) {
            int tempIdx = s.charAt(j);
            start = Math.max(start, charIdx.get(tempIdx) + 1);
            result = Math.max(result, j - start + 1);
            charIdx.put(tempIdx, j);
        }
        return result;

    }


    public static void main(String[] args) {
        String str="ddehiqkkodhb";
        for (int i = 0; i < str.length(); i++) {
            int a =str.charAt(i);
            System.out.println(a);
        }
        System.out.println(longestLen("abcabcg"));
    }


}
