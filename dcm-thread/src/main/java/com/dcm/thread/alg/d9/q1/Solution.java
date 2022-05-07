package com.dcm.thread.alg.d9.q1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
class Solution {

    public int longestValidParentheses(String s) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(i - stack.peek(), ans);
                }
            }
        }
        return ans;
    }
}