package com.dcm.thread.alg.d1.q2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型
 **/
public class ThreadB {

    private Lock lock = new ReentrantLock();


    private static Integer count = 0;

    private static final Integer MAX = 10;

    class Producer implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();

                try {
                    count++;


                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

                System.out.println(Thread.currentThread().getName() + "生产者生产，目前共有:" + count);

            }


        }
    }

    class Consumer implements Runnable {


        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    count--;

                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前共有:" + count);

                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }


            }
        }
    }


    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        new Thread(threadB.new Producer()).start();
        new Thread(threadB.new Producer()).start();

        new Thread(threadB.new Consumer()).start();
        new Thread(threadB.new Consumer()).start();


    }
}
