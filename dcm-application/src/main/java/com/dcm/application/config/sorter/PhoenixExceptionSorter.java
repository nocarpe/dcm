package com.dcm.application.config.sorter;

import com.alibaba.druid.pool.ExceptionSorter;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoenixExceptionSorter implements ExceptionSorter {
    private static final Logger log = LoggerFactory.getLogger(PhoenixExceptionSorter.class);

    public PhoenixExceptionSorter() {
    }
    @Override
    public boolean isExceptionFatal(SQLException e) {
        if (e.getMessage().contains("Connection is null or closed")) {
            log.info("剔除phoenix不可用的连接", e);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void configFromProperties(Properties properties) {

    }
}
