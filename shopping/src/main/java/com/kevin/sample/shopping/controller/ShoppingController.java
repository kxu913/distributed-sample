package com.kevin.sample.shopping.controller;


import com.kevin.sample.common.annotation.Xid;

import com.kevin.sample.domain.Order;

import com.kevin.sample.domain.Shopping;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import java.util.Map;


@RestController
@AllArgsConstructor
@Slf4j
public class ShoppingController {

    @Autowired
    private final RestTemplate restTemplate;
    @PostMapping("/shopping")

    @GlobalTransactional(name = "shopping-group",timeoutMills = 300000 )
    @ShardingSphereTransactionType(TransactionType.BASE)
    @Xid
    @ResponseBody
    public Map<String,Object>  getOrders(@RequestBody Shopping shopping){
        String xid = RootContext.getXID();
        log.info("xid: {}",xid);
        HttpHeaders headers = new HttpHeaders();
        headers.add("xid", xid);
        Order order = shopping.getOrder();
        order.setInventoryId(shopping.getInventoryId());
        HttpEntity<Order> entity = new HttpEntity<>(order,headers);
        ResponseEntity<Long> id = restTemplate.postForEntity("http://order/order",entity,Long.class);
        HttpEntity<Void> inventoryEntity = new HttpEntity<>(null,headers);
        restTemplate.put("http://inventory/inventory/"+shopping.getInventoryId(),inventoryEntity);
        Map<String,Object> rtn = new HashMap<>();
        rtn.put("orderId",id.getBody());
        rtn.put("inventory",shopping.getInventoryId());
        return rtn;
    }
}
