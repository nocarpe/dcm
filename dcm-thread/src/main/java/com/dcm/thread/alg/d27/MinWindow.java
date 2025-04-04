package com.dcm.thread.alg.d27;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2025/2/12
 * @description :
 **/
public class MinWindow {

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tlen = t.length();
        for (int i = 0; i < tlen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int slen = s.length();
        while (r < slen) {
            ++r;

        }
        return "";
    }
}
