package com.dcm.thread.disruptor;

import com.dcm.thread.data.TaskEvent;
import com.lmax.disruptor.RingBuffer;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class EventProducer {


    private final RingBuffer<TaskEvent> ringBuffer;

    public EventProducer(RingBuffer<TaskEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publishEvent(int id, String name) {
        long seq = ringBuffer.next();
        try {
            TaskEvent taskEvent = ringBuffer.get(seq);
            taskEvent.setId(id);
            taskEvent.setName(name);
        } finally {
            ringBuffer.publish(seq);
        }
    }


}
