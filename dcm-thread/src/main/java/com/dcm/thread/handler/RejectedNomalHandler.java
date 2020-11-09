package com.dcm.thread.handler;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class RejectedNomalHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("========="+executor.isTerminating());
        throw new RejectedExecutionException("Task " + r.toString() +
            " rejected from " +
            executor.toString());
    }
}
