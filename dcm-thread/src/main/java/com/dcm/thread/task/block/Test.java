package com.dcm.thread.task.block;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        BlockTask blockTask = new BlockTask();
        int num = 0;
        do {
            num++;
            System.out.println(num);
            blockTask.addTask(1);
        } while (num < 1000000);

    }
}
