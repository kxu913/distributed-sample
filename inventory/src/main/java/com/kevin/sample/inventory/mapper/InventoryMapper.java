package com.kevin.sample.inventory.mapper;

import com.kevin.sample.domain.Inventory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InventoryMapper {
    @Results(id = "inventory", value = {
            @Result(property = "inventoryId",column = "inventory_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "pcs",column = "pcs"),
            @Result(property = "status",column = "status"),
            @Result(property = "createdTime",column = "created_time"),
    })
    @Select("select * from t_inventory order by created_time desc")
    public List<Inventory> getInventories();

    @Insert("insert into t_inventory(name, pcs, status, created_time) values(#{name},#{pcs},#{status},#{createdTime})")
    public int saveInventory(Inventory inventory);

    @Update("update t_inventory set pcs = #{pcs} where inventory_id=#{id}")
    public int updateInventory( @Param("id") long id, @Param("pcs") int pcs);
}
