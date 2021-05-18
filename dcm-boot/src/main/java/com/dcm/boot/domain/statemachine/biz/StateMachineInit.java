package com.dcm.boot.domain.statemachine.biz;

import com.dcm.boot.domain.statemachine.config.BaseStateMachineKey;
import com.dcm.boot.domain.statemachine.execute.StateMachine;
import com.dcm.boot.domain.statemachine.execute.StateMachineConfig;
import com.dcm.boot.domain.statemachine.execute.StateMachineFactory;
import com.dcm.boot.domain.statemachine.handler.CarHireHandler;
import com.dcm.boot.domain.statemachine.handler.CarIdleHandler;
import com.dcm.boot.domain.statemachine.handler.IHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
@Component
public class StateMachineInit implements CommandLineRunner {

    @Autowired
    private CarIdleHandler carIdleHandler;

    @Autowired
    private CarHireHandler carHireHandler;

    @Override
    public void run(String... args) throws Exception {
        StateMachineFactory.register(CarApplyContextConstants.CAR_STATEMACHINE, build());
    }

    private StateMachine build() {
        StateMachineConfig<CarStatus, CarEvent, IHandler> stateMachineConfig = new StateMachineConfig();

        stateMachineConfig.from(CarStatus.IDLE)
            .apply(CarEvent.IN_STOCK)
            .handle(carIdleHandler)
            .to(CarStatus.IDLE).build();

        stateMachineConfig.from(CarStatus.IDLE)
            .apply(CarEvent.SUBMIT_HIRE_APPLY)
            .handle(carHireHandler)
            .to(CarStatus.HIRE).build();


        stateMachineConfig.from(CarStatus.IDLE)
            .apply(CarEvent.SUBMIT_REPAIR_APPLY)
            .handle(carHireHandler)
            .to(CarStatus.HIRE).build();



        return new StateMachine(stateMachineConfig);
    }
}
