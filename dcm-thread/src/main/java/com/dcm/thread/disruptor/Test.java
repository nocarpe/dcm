package com.dcm.thread.disruptor;

import com.dcm.thread.data.TaskEvent;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class Test extends AbstractElement<TaskEvent> {


    public static void main(String[] args) {
        // 处理Event的handler
        EventConsumer eventHandler = new EventConsumer();
        // 创建disruptor，采用单生产者模式
        Disruptor<TaskEvent> disruptor = new Test().init(new TaskEventFactory(), eventHandler);
        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<TaskEvent> ringBuffer = disruptor.getRingBuffer();

        EventProducer eventProducer = new EventProducer(ringBuffer);
        int i = 0;

        do {
            CompletableFuture.runAsync(() -> {
                int j = 0;
                do {
                    eventProducer.publishEvent(j, "hha");
                    j++;
                } while (j < 1000);
            });
            i++;
        } while (i < 100000);


    }
}
