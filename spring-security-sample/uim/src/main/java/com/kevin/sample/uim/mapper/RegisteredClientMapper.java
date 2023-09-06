/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.uim.domain.SampleRegisteredClient;
import com.kevin.sample.uim.mapper.handler.ArrayListHandler;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface RegisteredClientMapper {

    @Results(id = "RegisteredClient", value = {

            @Result(property = "id", column = "id"),
            @Result(property = "clientId", column = "client_id"),
            @Result(property = "clientSecret", column = "client_secret"),
            @Result(property = "grantTypes", column = "grant_types", typeHandler = ArrayListHandler.class),
            @Result(property = "authMethods", column = "auth_methods", typeHandler = ArrayListHandler.class),
            @Result(property = "scope", column = "scope", typeHandler = ArrayListHandler.class),
            @Result(property = "enabled", column = "enabled"),
    })

    @Select("select * from registered_client")
    public List<SampleRegisteredClient> getRegisteredClients();

    @Insert("insert into registered_client(id, client_id, client_secret, grant_types, auth_methods, scope, enabled) " +
            "values(" +
            "#{id}, " +
            "#{clientId}, " +
            "#{clientSecret}, " +
            "#{grantTypes, typeHandler=com.kevin.sample.uim.mapper.handler.ArrayListHandler}, " +
            "#{authMethods, typeHandler=com.kevin.sample.uim.mapper.handler.ArrayListHandler}, " +
            "#{scope, typeHandler=com.kevin.sample.uim.mapper.handler.ArrayListHandler}, " +
            "#{enabled})")
    public void insert(SampleRegisteredClient client);
}