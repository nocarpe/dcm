package com.dcm.thread.alg.d8.q1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作
 *
 * FIFO
 *
 * LIFO
 */
class MyQueue {

    private Deque<Integer> stack1;
    private Deque<Integer> stack2;


    public MyQueue() {
        stack1 = new ArrayDeque<Integer>();
        stack2 = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();

    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
