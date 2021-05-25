package com.dcm.boot.domain.statemachine.execute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
public class StateConfiguration<S, E, H> {

    private S currentState;
    private Map<E, H> eventHandleMap;

    private Map<E, S> nextStateMap;

    public StateConfiguration(S state) {
        this.currentState = state;
        eventHandleMap = new HashMap<E, H>(8);
        nextStateMap = new HashMap<E, S>(8);
    }

    public void configEventHandle(E e, H h) {
        eventHandleMap.put(e, h);
    }

    public void configEventNextState(E e, S s) {
        nextStateMap.put(e, s);
    }

    public H getHandle(E e) {
        return eventHandleMap.get(e);
    }

    public S getNextState(E e) {
        return nextStateMap.get(e);
    }

    public S getCurrentState() {
        return currentState;
    }
}
