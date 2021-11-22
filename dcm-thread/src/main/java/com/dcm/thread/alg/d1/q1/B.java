package com.dcm.thread.alg.d1.q1;

/**
 * 不提前创建，双重检查加锁，线程安全
 */
public class B {

    private static volatile B b;

    private B() {
    }


    public static B getInstance() {
        if (b == null) {
            synchronized (B.class) {
                if (b == null) {
                    b = new B();
                }
            }
        }

        return b;
    }


}
