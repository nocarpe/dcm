package com.dcm.application.config.prop;

/**
 * @author : yaoximing
 * @date : 2020-11-24
 * @description :
 **/
public class DruidDataSourceProperties {
    private String name;
    private String url;
    private String username;
    private String password;
    private String pwdPublicKey;
    private int initialSize = 5;
    private int maxActive = 50;
    private int minIdle = 5;
    private int maxWait = 60000;
    private int timeBetweenEvictionRunsMillis = 60000;
    private int minEvictableIdleTimeMillis = 300000;
    private int maxEvictableIdleTimeMillis = 25200000;
    private String validationQuery = "SELECT 'x'";
    private int validationQueryTimeout = 3000;
    private boolean testWhileIdle = true;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean poolPreparedStatements = false;
    private int maxOpenPreparedStatements = 20;
    private boolean enableMonitor = true;
    private boolean logSlowSql = true;
    private int slowSqlMillis = 1000;
    private boolean mergeSql = false;
    private String dbType;
    private String driverClassName;
    private boolean enableWallFilter = false;
    private boolean wallLogViolation = false;
    private boolean wallThrowException = true;

    public DruidDataSourceProperties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdPublicKey() {
        return pwdPublicKey;
    }

    public void setPwdPublicKey(String pwdPublicKey) {
        this.pwdPublicKey = pwdPublicKey;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(int maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(int validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
        this.maxOpenPreparedStatements = maxOpenPreparedStatements;
    }

    public boolean isEnableMonitor() {
        return enableMonitor;
    }

    public void setEnableMonitor(boolean enableMonitor) {
        this.enableMonitor = enableMonitor;
    }

    public boolean isLogSlowSql() {
        return logSlowSql;
    }

    public void setLogSlowSql(boolean logSlowSql) {
        this.logSlowSql = logSlowSql;
    }

    public int getSlowSqlMillis() {
        return slowSqlMillis;
    }

    public void setSlowSqlMillis(int slowSqlMillis) {
        this.slowSqlMillis = slowSqlMillis;
    }

    public boolean isMergeSql() {
        return mergeSql;
    }

    public void setMergeSql(boolean mergeSql) {
        this.mergeSql = mergeSql;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public boolean isEnableWallFilter() {
        return enableWallFilter;
    }

    public void setEnableWallFilter(boolean enableWallFilter) {
        this.enableWallFilter = enableWallFilter;
    }

    public boolean isWallLogViolation() {
        return wallLogViolation;
    }

    public void setWallLogViolation(boolean wallLogViolation) {
        this.wallLogViolation = wallLogViolation;
    }

    public boolean isWallThrowException() {
        return wallThrowException;
    }

    public void setWallThrowException(boolean wallThrowException) {
        this.wallThrowException = wallThrowException;
    }
}
