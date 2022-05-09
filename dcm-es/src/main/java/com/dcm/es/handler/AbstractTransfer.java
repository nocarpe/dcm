package com.dcm.es.handler;

import com.dcm.es.entity.BinlogDataDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2022/5/9
 **/
public abstract class AbstractTransfer implements Transfer {
    public Map<String, Map<String, Object>> getData(BinlogDataDto data) {

        Map<String, Map<String, Object>> map = new HashMap<>();

        map.put(getPrimaryKey(data), data.getData());
        return map;
    }

    public String getIndex(String database, String table) {
        return "";
    }

    public String getPrimaryKey(BinlogDataDto data) {
        String key = "";


        return key;
    }

    public List<String> getDeleteIds(BinlogDataDto data) {
        return Collections.singletonList(getPrimaryKey(data));
    }
}
