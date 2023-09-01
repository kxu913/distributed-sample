/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.domain.Group;
import com.kevin.sample.domain.UserGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserGroupMapper {

    @Results(id = "UserGroup", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "deleted", column = "deleted"),
    })

    @Select("select ug.user_id, ug.group_id, ug.deleted, g.group_name from t_user_group ug "
            + "inner join t_group g on ug.group_id = g.group_id "
            + "where ug.user_id = #{userId}"
    )
    public List<UserGroup> getUserGroup(@Param("userId") long userId);

    @Insert("insert into t_user_group( user_id,  group_id, deleted) values( #{userId},  #{groupId}, false)")
    public int bind(@Param("userId") long userId, @Param("groupId") int groupId);
}
