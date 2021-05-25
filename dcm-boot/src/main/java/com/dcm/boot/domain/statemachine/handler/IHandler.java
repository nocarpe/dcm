package com.dcm.boot.domain.statemachine.handler;

import com.dcm.boot.domain.statemachine.execute.StateContext;
import com.dcm.boot.domain.statemachine.execute.StateMachine;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
public interface IHandler {

    void handle(StateContext context, StateMachine stateMachine);


}
