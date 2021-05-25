package com.dcm.thread.disruptor;

import com.dcm.thread.data.TaskEvent;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.ThreadFactory;

/**
 * @author : yaoximing
 * @date : 2020/10/20
 * @description :
 **/
public abstract class AbstractElement<T> {

    // 指定RingBuffer的大小
    private int bufferSize = Double.valueOf(Math.pow(2, 20)).intValue();

    // 阻塞策略
    private final BlockingWaitStrategy strategy = new BlockingWaitStrategy();


    protected Disruptor<T> init(EventFactory<T> eventFactory, EventHandler<T> eventHandler) {
        // 创建disruptor，采用单生产者模式
        Disruptor<T> disruptor = new Disruptor(eventFactory, bufferSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "disruptor");
            }
        }, ProducerType.SINGLE, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(eventHandler);
        // 启动disruptor的线程
        return disruptor;
    }

}
