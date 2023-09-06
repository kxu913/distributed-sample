/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;


import com.kevin.sample.uim.domain.GroupRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupRoleMapper {

    @Results(id = "GroupRole", value = {
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "deleted", column = "deleted"),
    })

    @Select("select gr.group_id, gr.role_id, gr.deleted, g.group_name, r.role_name from t_group_role gr "
            + "inner join t_group g on gr.group_id = g.group_id "
            + "inner join sys_role r on gr.role_id = r.role_id "
            + "where gr.group_id = #{groupId}"
    )
    public List<GroupRole> getGroupRole(@Param("groupId") int groupId);

    @Insert("insert into t_group_role(group_id, role_id, deleted) values(#{groupId}, #{roleId}, false)")
    public int bind(@Param("groupId") int groupId, @Param("roleId") int roleId);
}
