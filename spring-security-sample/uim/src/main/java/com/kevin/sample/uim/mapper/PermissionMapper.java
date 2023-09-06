/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.uim.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PermissionMapper {

    @Results(id = "Permission", value = {
    
            @Result(property = "id",column = "id"),
            @Result(property = "operator",column = "operator"),
            @Result(property = "resourceId",column = "resource_id"),
            @Result(property = "enabled",column = "enabled"),
    })

    @Select("select * from sys_permission")
    public List<Permission> getPermission();

    @Insert("insert into sys_permission( operator, resource_id,  enabled) values( #{operator}, #{resourceId}, true) returning id" )
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    public long savePermission(Permission domain);
}
