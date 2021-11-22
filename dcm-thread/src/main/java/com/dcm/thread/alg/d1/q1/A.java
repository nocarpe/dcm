package com.dcm.thread.alg.d1.q1;

/**
 * 饿汉式，提前创建，用空间换时间,线程安全
 */
public class A {

    private static A a = new A();

    private A() {
    }

    public static A getInstance() {
        return a;
    }
}
