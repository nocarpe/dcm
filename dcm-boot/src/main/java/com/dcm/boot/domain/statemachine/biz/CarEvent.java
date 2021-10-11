package com.dcm.boot.domain.statemachine.biz;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
public enum CarEvent {
    IN_STOCK,
    SUBMIT_HIRE_APPLY,
    HIRE_APPLY_AGREE,
    SUBMIT_REPAIR_APPLY,
    REPAIR_APPLY_AGREE,
    SUBMIT_SCRAP_APPLY,
    SCRAP_APPLY_AGREE,
    SUBMIT_MANAGE_APPLY,
    MANAGE_APPLY_AGREE,
    SUBMIT_BUYOUT_APPLY,
    BUYOUT_APPLY_AGREE,
    SURRENDER_APPLY,
    ;


    public static CarEvent build(int num) {
        CarEvent carEvent = null;
        switch (num) {
            case 1:
                carEvent = IN_STOCK;
                break;
            case 2:
                carEvent = SURRENDER_APPLY;
                break;
            default:
                break;

        }
        return carEvent;
    }
}
