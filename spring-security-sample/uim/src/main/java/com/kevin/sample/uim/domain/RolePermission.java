package com.kevin.sample.uim.domain;

import lombok.Data;

@Data
public class RolePermission {
    private int roleId;

    private String roleName;

    private int resourceId;

    private int permissionId;

    private String operator;

    private String label;

    private String path;

    private String component;

    public ResourcePermission mixinRolePermission(){
        ResourcePermission p = new ResourcePermission();
        p.setPermissionId(this.permissionId);
        p.setResourceId(this.resourceId);
        p.setOperator(this.operator);
        p.setLabel(this.label);
        p.setPath(this.path);
        p.setComponent(this.component);
        return p;
    }
}
