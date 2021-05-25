package com.dcm.thread.disruptor;

import com.dcm.thread.data.TaskData;
import com.dcm.thread.data.TaskEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class EventConsumer implements EventHandler<TaskEvent> {

    @Override
    public void onEvent(TaskEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getId() + "==" + sequence + "---" + endOfBatch);
    }
}
