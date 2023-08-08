package com.kevin.shardingsphere.mapper;

import com.kevin.shardingsphere.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Results(id = "order", value = {
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "description",column = "description"),
            @Result(property = "createdTime",column = "created_time"),
    })

    @Select("select * from t_order order by order_id desc")
    public List<Order> getOrder();

    @Insert("insert into t_order(order_id,user_id,description,created_time) values(#{orderId},#{userId},#{description},#{createdTime})")
    public int saveOrder(Order order);
}
