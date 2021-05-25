package com.dcm.boot.domain;

import com.dcm.boot.MainTest;
import com.dcm.boot.domain.statemachine.bean.ElectricCar;
import com.dcm.boot.domain.statemachine.biz.CarApplyContextConstants;
import com.dcm.boot.domain.statemachine.biz.CarEvent;
import com.dcm.boot.domain.statemachine.biz.CarStatus;
import com.dcm.boot.domain.statemachine.execute.StateContext;
import com.dcm.boot.domain.statemachine.execute.StateMachineFactory;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author : yaoximing
 * @date : 2021-03-31
 * @description :
 **/
public class StateMachineTest extends MainTest {


    @Test
    public void test() {
        StateContext stateContext = new StateContext();
        ElectricCar electricCar = new ElectricCar();
        stateContext.setData(CarApplyContextConstants.CAR_APPLY, electricCar);
        stateContext.setData(CarApplyContextConstants.CURRENT_STATE, CarStatus.HIRE);
        StateMachineFactory.getStateMachine(CarApplyContextConstants.CAR_STATEMACHINE)
            .execute(CarStatus.MANAGE, stateContext);

        System.out.println(111);
    }


    @Test
    public void test1() {
        StateContext stateContext = new StateContext();
        ElectricCar electricCar = new ElectricCar();
        stateContext.setData(CarApplyContextConstants.CAR_APPLY, electricCar);
        stateContext.setData(CarApplyContextConstants.CURRENT_STATE, CarStatus.IDLE);
        StateMachineFactory.getStateMachine(CarApplyContextConstants.CAR_STATEMACHINE)
            .execute(CarEvent.SUBMIT_HIRE_APPLY, stateContext);

        System.out.println(111);
    }

}
