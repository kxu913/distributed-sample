package com.kevin.sample.uim.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class ResourcePermission {

    private int permissionId;

    private int resourceId;

    private String operator;

    private String label;

    private String path;

    private String component;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourcePermission that = (ResourcePermission) o;
        return resourceId == that.resourceId && Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, operator);
    }
}
