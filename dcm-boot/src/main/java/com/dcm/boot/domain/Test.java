package com.dcm.boot.domain;

import com.dcm.boot.domain.statemachine.biz.CarEvent;

/**
 * @author : yaoximing
 * @date : 2021/8/4
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        CarEvent carEvent =CarEvent.build(2);
        System.out.println(PositionTypeEnum.SALE.toString());

    }

    public enum PositionTypeEnum{
        SALE,
        CALL_BACK,
        TRANSITION,
        SELF_USE,
        DAMAGE
    }
}
