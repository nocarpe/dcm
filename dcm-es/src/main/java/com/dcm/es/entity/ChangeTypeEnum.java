package com.dcm.es.entity;

import java.util.Arrays;

public enum ChangeTypeEnum {
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    BOOT_INSERT("bootstrap-insert"),;
    private String type;

    ChangeTypeEnum(String type) {
        this.type = type;
    }

    public static ChangeTypeEnum getByType(String type) {
        return Arrays.stream(ChangeTypeEnum.values()).filter(o -> o.type.equalsIgnoreCase(type)).findAny().orElse(null);
    }
}