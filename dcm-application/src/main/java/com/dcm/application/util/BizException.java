package com.dcm.application.util;

/**
 * @author : yyyao
 * @date : 2022/5/22
 **/
public class BizException extends RuntimeException{

    private BizEnum errorCode;

    private String errorMessage;

    public BizException() {
    }

    public BizException(BizEnum errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMessage = errorMsg;
    }

    public BizEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BizEnum errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMsg) {
        this.errorMessage = errorMsg;
    }
}

