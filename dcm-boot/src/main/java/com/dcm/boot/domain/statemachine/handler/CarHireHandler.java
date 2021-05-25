package com.dcm.boot.domain.statemachine.handler;

import com.dcm.boot.domain.statemachine.execute.StateContext;
import com.dcm.boot.domain.statemachine.execute.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @author : yaoximing
 * @date : 2021-03-30
 * @description :
 **/
@Component
public class CarHireHandler implements IHandler {

    @Override
    public void handle(StateContext context, StateMachine stateMachine) {
        System.out.println("创建租用申请");

    }
}
