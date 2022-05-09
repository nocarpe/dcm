package com.dcm.es.handler;

import com.dcm.es.entity.BinlogDataDto;

/**
 **/
public interface IHandler {
    boolean interest(String database, String table);

    void handle(BinlogDataDto data);
}
