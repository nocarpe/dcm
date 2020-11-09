package com.dcm.thread.disruptor;

import com.dcm.thread.data.TaskEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class TaskEventFactory implements EventFactory<TaskEvent> {

    @Override
    public TaskEvent newInstance() {
        return new TaskEvent();
    }
}
