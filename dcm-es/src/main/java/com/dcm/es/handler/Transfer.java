package com.dcm.es.handler;

import com.dcm.es.entity.BinlogDataDto;
import java.util.List;
import java.util.Map;

/**

 **/
public interface Transfer {
    boolean interest(String database, String table);

    String getIndex(String database, String table);

    String getPrimaryKey(BinlogDataDto data);

    Map<String, Map<String, Object>> getData(BinlogDataDto data);

    List<String> getDeleteIds(BinlogDataDto data);
}
