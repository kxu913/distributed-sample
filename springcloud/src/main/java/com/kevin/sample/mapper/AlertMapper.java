package com.kevin.sample.mapper;

import com.kevin.sample.domain.Alert;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface AlertMapper {



    @Select("SELECT id, user_id, alerttype, symbol, user_input, status, created_time, updated_time FROM alert WHERE user_id=#{userId} ORDER by alerttype")
    @Results(id = "alertMap", value = {
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "alertType",column = "alerttype"),
            @Result(property = "symbol",column = "symbol"),
            @Result(property = "userInput",column = "user_input"),
            @Result(property = "status",column = "status"),
            @Result(property = "createdTime",column = "created_time"),
            @Result(property = "updatedTime",column = "updated_time")
    })
    public List<Alert> getAlerts(String userId);


    @Select("SELECT * FROM alert WHERE alerttype=#{alertType} ORDER BY user_id")
    @ResultMap("alertMap")
    public List<Alert> getAlertsByType(String alertType);
}
