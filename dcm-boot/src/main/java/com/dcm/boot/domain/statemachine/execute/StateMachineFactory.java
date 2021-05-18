package com.dcm.boot.domain.statemachine.execute;

import com.dcm.boot.domain.statemachine.handler.IHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description : 状态机工厂
 **/
public final class StateMachineFactory {

    private static Map<String, StateMachine<Object, Object, IHandler>> stateMachineMap = new HashMap<>();

    public StateMachineFactory() {

    }

    public static void register(String key, StateMachine stateMachine) {
        stateMachineMap.put(key, stateMachine);
    }

    public static StateMachine<Object, Object, IHandler> getStateMachine(String key) {
        return stateMachineMap.get(key);
    }

}
