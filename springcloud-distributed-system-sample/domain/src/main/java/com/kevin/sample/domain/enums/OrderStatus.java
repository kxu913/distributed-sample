package com.kevin.sample.domain.enums;

public enum OrderStatus implements KeyValueEnum {
    CREATED,CANCELLED,PAID,SHIPPED;

    @Override
    public String getKey() {
        return this.toString();
    }

    @Override
    public String getValue() {
        return this.toString();
    }
}
