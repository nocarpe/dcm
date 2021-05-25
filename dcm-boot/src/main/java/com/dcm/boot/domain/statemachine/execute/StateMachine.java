package com.dcm.boot.domain.statemachine.execute;

import com.dcm.boot.domain.statemachine.config.BaseStateMachineKey;
import com.dcm.boot.domain.statemachine.handler.IHandler;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
public class StateMachine<S, E, H extends IHandler> {

    private final StateMachineConfig<S, E, H> stateMachineConfig;

    public StateMachine(StateMachineConfig<S, E, H> config) {
        this.stateMachineConfig = config;
    }


    public void execute(E event, StateContext context) {
        S mainStateEnum = (S) context.getData(BaseStateMachineKey.CURRENT_STATE);
        if (null == mainStateEnum) {
            throw new RuntimeException("未找到下一个状态，无法执行");
        }
        H handle = stateMachineConfig.getHandle(mainStateEnum, event);
        if (null == handle) {
            throw new RuntimeException(String.format("状态和操作不匹配,state=[%s],event=[%s]", mainStateEnum, event));
        }
        S nextState=stateMachineConfig.getNextState(mainStateEnum, event);
        context.setData(BaseStateMachineKey.CURRENT_STATE,nextState);
        handle.handle(context,this);
    }

}
