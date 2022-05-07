package com.dcm.thread.alg.d1.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替输出
 */
public class ThreadA implements Runnable {

    private Lock lock = new ReentrantLock();

    private static String[] a = {"A", "B"};

    private static Integer count = 0;

    private static Integer MAX = 500;
    private String name;

    public ThreadA(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (count < MAX) {
            //System.out.println(1);
            lock.lock();
            try {
                while (this.name.equals(a[count % 2]) && count < MAX) {
                    System.out.println(name + ",count:" + count);
                    count++;
                }
            } catch (Exception e) {
                //
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
       //new Thread(new ThreadA("A")).start();
        //new Thread(new ThreadA("B")).start();
        TestName testName1 =new TestName();
        testName1.setName("A");
        testName1.setId(2);
        TestName testName2 = new TestName();
        testName2.setName("A");
        testName2.setId(3);
        List<TestName> list = new ArrayList<>();
        list.add(testName1);
        list.add(testName2);

       Optional<TestName> optional= list.stream().filter(TestName::check).findAny();
        System.out.println(optional.isPresent());
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//        executor.execute(new ThreadA("A"));
//        executor.execute(new ThreadA("B"));
//        executor.shutdown();
    }
}

