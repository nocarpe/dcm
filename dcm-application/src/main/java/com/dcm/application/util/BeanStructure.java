package com.dcm.application.util;

import java.util.HashMap;
import java.util.Map;

public class BeanStructure {
    private Map<String, BeanProperty> properties = new HashMap();

    public BeanStructure() {
    }

    public Map<String, BeanProperty> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, BeanProperty> properties) {
        this.properties = properties;
    }
}