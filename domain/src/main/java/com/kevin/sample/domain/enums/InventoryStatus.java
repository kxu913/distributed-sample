package com.kevin.sample.domain.enums;

public enum InventoryStatus implements KeyValueEnum{

    ACTIVE, INACTIVE;

    @Override
    public String getKey() {
        return this.toString();
    }

    @Override
    public String getValue() {
        return this.toString();
    }
}
