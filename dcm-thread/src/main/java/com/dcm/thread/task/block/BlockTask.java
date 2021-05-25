package com.dcm.thread.task.block;

import com.dcm.thread.data.TaskData;
import com.dcm.thread.task.AbstractNomalTask;

/**
 * @author : yaoximing
 * @date : 2020-11-09
 * @description :
 **/
public class BlockTask extends AbstractNomalTask<Integer> {

    @Override
    public Integer doTask(Integer data) {
        System.out.println("处理任务.." + data);
        return 1;
    }
}
