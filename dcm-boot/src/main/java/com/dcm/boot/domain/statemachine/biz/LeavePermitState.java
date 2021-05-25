package com.dcm.boot.domain.statemachine.biz;

public enum LeavePermitState {
    SUBMIT_PERMIT,//提交假单
    LEADER_PERMIT,//领导审批
    CEO_PERMIT,//CEO审批
}