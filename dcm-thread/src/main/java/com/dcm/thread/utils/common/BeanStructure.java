package com.dcm.thread.utils.common;

import java.beans.BeanProperty;
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