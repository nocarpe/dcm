package com.dcm.boot.domain.statemachine.bean;


/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/

public class ElectricCar {

    private String assetCode;
    private String status;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
