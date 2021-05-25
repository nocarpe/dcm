package com.dcm.boot.domain.statemachine.handler;

import com.dcm.boot.domain.statemachine.bean.ElectricCar;
import com.dcm.boot.domain.statemachine.biz.CarApplyContextConstants;
import com.dcm.boot.domain.statemachine.execute.StateContext;
import com.dcm.boot.domain.statemachine.execute.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
@Component
public class CarIdleHandler implements IHandler {

    @Override
    public void handle(StateContext context, StateMachine stateMachine) {
        ElectricCar electricCar = (ElectricCar) context.getData(CarApplyContextConstants.CAR_APPLY);
        //..
        electricCar.setStatus("IDLE");
    }
}
