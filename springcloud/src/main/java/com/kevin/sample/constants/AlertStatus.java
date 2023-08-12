package com.kevin.sample.constants;

public enum AlertStatus implements KeyValueEnum{
    ACTIVE,INACTIVE,SUSPEND;

    @Override
    public String getKey() {
        return this.toString();
    }

    @Override
    public String getValue() {
        return this.toString();
    }
}
