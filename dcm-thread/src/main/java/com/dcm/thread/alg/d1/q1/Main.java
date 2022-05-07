package com.dcm.thread.alg.d1.q1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();

        new Thread(new ThreadP(1)).start();
        new Thread(new ThreadP(2)).start();
        new Thread(new ThreadP(0)).start();



    }


    static class ThreadP implements Runnable {

        private Lock lock = new ReentrantLock();
        private Integer num;
        private static Long count = 1L;

        public ThreadP(Integer num) {
            this.num = num;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count % 3 == num) {
                        System.out.println(num == 0 ? 3 : num);
                        count++;
                    }
                } catch (Exception ex) {

                } finally {
                    lock.unlock();
                }

            }

        }
    }

}