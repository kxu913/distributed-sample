/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.uim.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RoleMapper {

    @Results(id = "Role", value = {
    
            @Result(property = "roleId",column = "role_id"),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "description",column = "description"),
    })

    @Select("select * from sys_role")
    public List<Role> getRole();

    @Insert("insert into sys_role( role_name,  description) values( #{roleName},  #{description}) returning role_id" )
    @Options(useGeneratedKeys = true,keyProperty = "roleId", keyColumn = "role_id")
    public long saveRole(Role domain);
}
