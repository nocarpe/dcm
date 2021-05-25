package com.dcm.boot.domain.statemachine.execute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-03-29
 * @description :
 **/
public class StateContext {

    private Map<String, Object> dataMap;

    public StateContext() {
        this.dataMap = new HashMap<String, Object>();
    }

    public Object getData(String key){
        return dataMap.get(key);
    }

    public Object setData(String key,Object value){
        return dataMap.put(key,value);
    }
}
