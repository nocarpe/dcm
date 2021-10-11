package com.dcm.thread.task;

import com.dcm.thread.handler.RejectedNomalHandler;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description : 阻塞队列实现任务
 **/

public abstract class AbstractNomalTask<T> implements INomalTask<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractNomalTask.class);

    /**
     * 任务队列
     */
    protected BlockingDeque<T> dataQueue;
    /**
     * 调度任务
     */
    protected ScheduledExecutorService scheduledExecutorService;

    /**
     * 定时任务数,每次定时从队列中拉取的任务数
     */

    private int scheduleSize = 3000;

    /**
     * 定时任务线程池大小
     */

    private int schedulePoolCoreSize = scheduleSize;

    /**
     * 定时任务开始执行时间
     */
    private int scheduleInitialDelay = 0;

    /**
     * 定时任务间隔时间，队列定时执行任务之间的间隔
     */
    private int scheduleDelay = 5;
    /**
     * 执行线程池大小
     */
    private int threadPoolCoreSize = 10;
    /**
     * 执行线程池最大线程大小
     */
    private int theadPoolMaxSize = threadPoolCoreSize * 2;

    /**
     * 执行线程池的队列大小
     */
    private int executeThreadPoolQueueCount = 100000;

    /**
     * 执行线程
     */
    protected ThreadPoolExecutor threadPoolExecutor;


    public AbstractNomalTask() {
        this.dataQueue = new LinkedBlockingDeque<>();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(schedulePoolCoreSize);

        threadPoolExecutor = new ThreadPoolExecutor(threadPoolCoreSize, theadPoolMaxSize, 60l, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "处理任务线程-" + count.incrementAndGet());
            }
        }, new RejectedNomalHandler());

        initScheduleQueueConfig();
    }


    private void initScheduleQueueConfig() {
        for (int i = 0; i < scheduleSize; i++) {
            scheduledExecutorService
                .scheduleWithFixedDelay(new TaskHandler(), scheduleInitialDelay, scheduleDelay, TimeUnit.MILLISECONDS);
        }

    }


    public abstract Integer doTask(final T data);


    @Override
    public void addTask(T data) {
        if (data != null) {
            if (dataQueue.size() > Integer.MAX_VALUE) {
                log.error("当前任务数已超过队列最大数量{},任务丢失{}", Integer.MAX_VALUE, data);
            }
            try {
                dataQueue.put(data);
                log.info("当前队列长度{}", dataQueue.size());
            } catch (Exception e) {
                log.error("添加队列数据异常,堆栈:{}", e.getMessage());
            }
        } else {
            log.error("任务不能为空:{}", data);
        }
    }

    private final class TaskHandler implements Runnable {

        @Override
        public void run() {
            while (!dataQueue.isEmpty()) {
                try {
                    T data = dataQueue.take();
                    threadPoolExecutor.execute(() -> {
                        doTask(data);
                    });

                } catch (InterruptedException e) {
                    log.error("出队列异常{}", e.getMessage());
                }

            }
        }
    }

}
