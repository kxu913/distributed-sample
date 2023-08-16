package com.kevin.sample.order.mapper;



import com.kevin.sample.order.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Results(id = "order", value = {
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "sId",column = "sid"),
            @Result(property = "description",column = "description"),
            @Result(property = "createdTime",column = "created_time"),
    })

    @Select("select * from t_order order by order_id desc")
    public List<Order> getOrder();

    @Insert("insert into t_order(user_id, description,created_time) values(#{userId},#{description},#{createdTime})")
    public int saveOrder(Order order);
}
