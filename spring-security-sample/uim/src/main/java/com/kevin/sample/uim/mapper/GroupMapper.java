/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.uim.domain.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface GroupMapper {

    @Results(id = "Group", value = {
    
            @Result(property = "groupId",column = "group_id"),
            @Result(property = "groupName",column = "group_name"),
            @Result(property = "groupDescription",column = "group_description"),
    })

    @Select("select * from t_group")
    public List<Group> getGroup();

    @Insert("insert into t_group( group_name,  group_description) values( #{groupName},  #{groupDescription}) returning group_id" )
    @Options(useGeneratedKeys = true,keyProperty = "groupId", keyColumn = "group_id")
    public long saveGroup(Group domain);
}
