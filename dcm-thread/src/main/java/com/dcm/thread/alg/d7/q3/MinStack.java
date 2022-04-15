package com.dcm.thread.alg.d7.q3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 */
class MinStack {

    private Deque<Integer> dataStack;
    private Deque<Integer> minStack;


    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        dataStack.push(val);
        if (val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int x = dataStack.pop();
        if (x == minStack.peek()) {
            minStack.pop();
        }

    }

    public int top() {

        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}