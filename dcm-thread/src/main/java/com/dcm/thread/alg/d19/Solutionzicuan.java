package com.dcm.thread.alg.d19;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author : yyyao
 * @date : 2024/12/7
 * @description :无重复字符串的最长子串
 **/
public class Solutionzicuan {


    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int len = s.length();
        int start = 0;

        int[] allIndex = new int[128];
        for (int i = 0; i < 128; i++) {
            allIndex[i] = -1;
        }
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i);
            start = Math.max(start, allIndex[index] + 1);
            res = Math.max(res, i - start + 1);
            allIndex[index]=i;
        }
        return res;

    }

}