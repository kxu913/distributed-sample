/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.domain.GroupRole;
import com.kevin.sample.domain.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMapper {

    @Results(id = "RolePermission", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "permissionId", column = "p_id"),
            @Result(property = "resourceId", column = "resource_id"),
            @Result(property = "operator", column = "operator"),
            @Result(property = "label", column = "label"),
            @Result(property = "path", column = "path"),
            @Result(property = "component", column = "component"),
    })

    @Select("select rp.role_id, rp.permission_id as p_id, p.resource_id, r.role_name, p.operator, res.label, res.path, res.component "
            + "from sys_role_permission rp "
            + "inner join sys_role r on rp.role_id = r.role_id "
            + "inner join sys_permission p on rp.permission_id = p.id "
            + "inner join sys_resource res on p.resource_id = res.id "
            + "where rp.role_id = #{roleId}"
    )
    public List<RolePermission> getRolePermission(@Param("roleId") int roleId);

    @Insert("insert into sys_role_permission(role_id,permission_id) values(#{roleId}, #{pId})")
    public int bind( @Param("roleId") int roleId,@Param("pId") int permissionId);
}
