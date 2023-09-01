/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class Permission {

    private int id;

    private String operator;

    private int resourceId;

    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
