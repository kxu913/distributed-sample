package com.kevin.sample.message.mapper;

import com.kevin.sample.message.domain.SampleMessage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageMapper {
    @Insert("insert into t_message(id, body, tag,created_time) values(#{id},#{body},#{tag},#{createdTime}) returning id")
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    public int insert(SampleMessage message);


    @Select(("select count(1) as c from t_message where id =#{id}"))
    public int count(@Param("id") int id);
}
