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
            charIndex.put(tempIndex,i);
        }

        return result;

    }





    public static void main(String[] args) {
            System.out.println(longestLen("abcabcg"));
    }

}
