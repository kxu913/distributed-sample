/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.uim.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Results(id = "User", value = {

            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "enabled", column = "enabled"),
    })

    @Select("select * from t_user")
    public List<User> getUser();

    @Insert("insert into t_user(user_id, user_name, password,  enabled) values(#{userId}, #{userName}, #{password},  #{enabled}) returning user_id")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    public void saveUser(User domain);

    @Select("select * from t_user where user_name=#{name} limit 1")
    @ResultMap("User")
    public User getUserByName(@Param("name") String name);

    @Update("update t_user set password = #{password} where user_id = #{userId}")
    public void updatePassword(@Param("userId") long userId, @Param("password") String password);

    @Select("select * from t_user where user_name=#{name} and password=#{password} limit 1")
    @ResultMap("User")
    User getUserByNameAndPwd(@Param("name") String userName, @Param("password") String password);
    @Select("select * from t_user where user_id=#{userId} limit 1")
    @ResultMap("User")
    User getUserById(long userId);

//    select u.user_id, u.user_name, g.group_id, g.group_name,
//    r.role_id, r.role_name,
//    p.id as permission_id, p.operator,
//    res.id as res_id, res.label, res.path, res.component
//    from t_user u
//    inner join t_user_group ug on u.user_id = ug.user_id
//    inner join t_group g on ug.group_id = g.group_id
//    inner join t_group_role gr on ug.group_id = gr.group_id
//    inner join sys_role r on gr.role_id = r.role_id
//    inner join sys_role_permission rp on r.role_id = rp.role_id
//    inner join sys_permission p on rp.permission_id = p.id
//    inner join sys_resource res on p.resource_id = res.id
//    where u.user_name = 'kevin'
}
