package biz.pattern.chain;

import lombok.RequiredArgsConstructor;

/**
 * @author : yyyao
 * @date : 2024/8/11
 * @description : 用模板方法的设计模式来抽象流程，给出一个抽象的模板类，拆分执行流程，提供一个工厂类来组合这些方法提供业务类去调用
 **/
@RequiredArgsConstructor
public abstract class AbstractUserRightsTemplate {

    private final IdleUserRightsService userRightsService;

    /**
     * 1.前置流程
     */
    public abstract void beforeProcedure();

    /**
     * 2.公共流程
     */
    public void commonProcedure() {
        //写权益卡
        userRightsService.writeRightsCard();
        // 写流水记录
        userRightsService.wirteRightsHistory();
        // 充值IM权益
        userRightsService.rechargeIMRights();

    }

    /**
     * 3.业务特有流程
     */
    public abstract void specialProcedure();

    /**
     * 4.后置流程
     */
    public void afterProcedure() {
        //发送MQ消息通知下游
        userRightsService.sendMeteQMessage();
    }
}
