package com.dcm.boot.controller;

import com.dcm.boot.domain.statemachine.bean.ElectricCar;
import com.dcm.boot.domain.statemachine.biz.CarApplyContextConstants;
import com.dcm.boot.domain.statemachine.biz.CarStatus;
import com.dcm.boot.domain.statemachine.execute.StateContext;
import com.dcm.boot.domain.statemachine.execute.StateMachineFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yaoximing
 * @date : 2021-03-31
 * @description :
 **/
@RestController
@RequestMapping("dcm")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "ok!";
    }



    @GetMapping("/test1")
    public String test() {
        StateContext stateContext = new StateContext();
        ElectricCar electricCar = new ElectricCar();
        stateContext.setData(CarApplyContextConstants.CAR_APPLY, electricCar);
        stateContext.setData(CarApplyContextConstants.CURRENT_STATE, CarStatus.HIRE);
        StateMachineFactory.getStateMachine(CarApplyContextConstants.CAR_APPLY).execute(CarStatus.MANAGE, stateContext);

        System.out.println(111);
        return "ok!";
    }

}
