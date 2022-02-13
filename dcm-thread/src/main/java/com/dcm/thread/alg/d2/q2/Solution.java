package com.dcm.thread.alg.d2.q2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        if (s.length() % 2 != 0) {
            return false;
        }
        Queue<Character> deque = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '(' || chars[i] == '[') {
                deque.offer(chars[i]);
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                if ((chars[i] == '}' && deque.peek() == '{' || chars[i] == ')' && deque.peek() == '('
                    || chars[i] == ']' && deque.peek() == '[')) {
                    deque.poll();
                }else {
                    return false;
                }
            }
        }
        if (deque.isEmpty()) {
            return true;
        }

        return false;

    }


    public static boolean isValid2(String s) {
        char[] chars = s.toCharArray();
        if (s.length() % 2 != 0) {
            return false;
        }
        int half = s.length() / 2 - 1;
        int length = chars.length - 1;
        for (int i = half; i >= 0; i--) {
            if (!(chars[i] == '{' && chars[length - i] == '}' || chars[i] == '(' && chars[length - i] == ')'
                || chars[i] == '[' && chars[length - i] == ']')) {
                return false;
            }
        }

        return true;

    }


    public static void main(String[] args) {
        System.out.println(isValid("([}}])"));
    }


}