package com.dcm.es.handler;

/**
 **/
public class DefaultTransfer extends AbstractTransfer{

    @Override
    public boolean interest(String database, String table) {
        return false;
    }
}
