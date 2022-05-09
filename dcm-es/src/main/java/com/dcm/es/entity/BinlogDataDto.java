package com.dcm.es.entity;

import java.util.Map;

/**
 *
 **/

public class BinlogDataDto {
    /**
     * 数据库名
     */
    private String database;
    /**
     * 表名
     */
    private String table;
    /**
     * 数据变更类型
     */
    private String type;
    /**
     * 时间戳
     */
    private Long ts;
    /**
     * id
     */
    private Long xid;

    private Long xoffset;
    /**
     * 是否已提交
     */
    private boolean commit;
    /**
     * 新数据
     */
    private Map<String, Object> data;
    /**
     * 原数据
     */
    private Map<String, Object> old;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Long getXid() {
        return xid;
    }

    public void setXid(Long xid) {
        this.xid = xid;
    }

    public Long getXoffset() {
        return xoffset;
    }

    public void setXoffset(Long xoffset) {
        this.xoffset = xoffset;
    }

    public boolean isCommit() {
        return commit;
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getOld() {
        return old;
    }

    public void setOld(Map<String, Object> old) {
        this.old = old;
    }
}
