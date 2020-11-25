package com.dcm.application.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigMapConfigurationProperties {
    private Map<String, Object> configMap = new LinkedHashMap();

    public ConfigMapConfigurationProperties() {
    }

    public Map<String, Object> getConfigMap() {
        return this.configMap;
    }

    public void setConfigMap(final Map<String, Object> configMap) {
        this.configMap = configMap;
    }
}