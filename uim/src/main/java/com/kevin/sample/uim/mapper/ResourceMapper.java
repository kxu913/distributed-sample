/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.mapper;

import com.kevin.sample.domain.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceMapper {

    @Results(id = "Resource", value = {

            @Result(property = "id", column = "id"),
            @Result(property = "label", column = "label"),
            @Result(property = "path", column = "path"),
            @Result(property = "component", column = "component"),
            @Result(property = "global", column = "global"),
    })

    @Select("select * from sys_resource")
    public List<Resource> getResource();

    @Insert("insert into sys_resource(label, path, component,  global) values(#{label}, #{path}, #{component},  #{global}) returning id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public long saveResource(Resource domain);
}
