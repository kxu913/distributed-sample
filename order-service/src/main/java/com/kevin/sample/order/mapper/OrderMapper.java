package com.kevin.sample.order.mapper;



import com.kevin.sample.domain.Order;
import com.kevin.sample.domain.enums.OrderStatus;
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
            @Result(property = "status",column = "status"),
            @Result(property = "inventoryId", column = "inventory_id"),
    })

    @Select("select * from t_order order by order_id desc")
    public List<Order> getOrder();

    @Insert("insert into t_order(user_id, inventory_id, description,created_time, status) values(#{userId},#{inventoryId},#{description},#{createdTime},#{status}) returning order_id")
    @Options(useGeneratedKeys = true,keyProperty = "orderId", keyColumn = "order_id")
    public long saveOrder(Order order);
    @Update("update t_order set status = #{status} where order_id=#{orderId}")
    int updateOrderStatus(@Param("orderId") long orderId, @Param("status") OrderStatus status);
}
