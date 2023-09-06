package com.kevin.sample.seckill.service.impl;


import com.alibaba.fastjson.JSONObject;

import com.kevin.sample.seckill.domain.Inventory;
import com.kevin.sample.seckill.service.SecondKillService;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class SecondKillServiceImpl implements SecondKillService {

    @Autowired
    private RedisTemplate<Long, JSONObject> jsonRedisTemplate;
    @Autowired
    private RedisTemplate<Long, String> longStringRedisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic}")
    private String shoppingTopic;

    @Override
    public JSONObject startSecKillV2(long inventoryId) {
        Inventory inventory = restTemplate.getForObject("http://inventory/inventory/" + inventoryId, Inventory.class);
        assert inventory != null;
        DefaultRedisScript<JSONObject> script = new DefaultRedisScript<>();
        script.setResultType(JSONObject.class);
        script.setLocation(new ClassPathResource("/lua/start_seckill.lua"));
        List<Long> keys = new ArrayList<>();
        keys.add(inventoryId);
        keys.add((long) inventory.getPcs());
        keys.add((long) inventory.getSale());
        return jsonRedisTemplate.execute(script, keys);
    }

    @Override
    public String secondKillV2(String userId, long inventoryId) {
        DefaultRedisScript<String> script = new DefaultRedisScript<>();
        script.setResultType(String.class);
        script.setLocation(new ClassPathResource("/lua/seckill.lua"));
        List<Long> keys = new ArrayList<>();
        keys.add(Long.parseLong(userId));
        keys.add(inventoryId);
        String result = longStringRedisTemplate.execute(script, keys);
        String rtn = "unknown";
        switch (Objects.requireNonNull(result)) {
            case "GOT":
                sendOrderToMQ(userId, inventoryId);
                return "恭喜你，抢到了，为你生成订单中。";
            case "MISS":
                return "已经抢完了。";
            case "OWN":
                return "你已经抢到了，不要贪心哦。";
        }
        return rtn;
    }

    private void sendOrderToMQ(String userId, long inventoryId) {
        rocketMQTemplate.sendMessageInTransaction(shoppingTopic, MessageBuilder.withPayload(userId)
                .setHeader("inventory-id", inventoryId)
                .setHeader("user-id", userId)
                .setHeader("xid", UUID.randomUUID().toString())
                .build(), null);
        log.info("send a message include an order, userid is {}, inventory id is {}", userId, inventoryId);

    }

}
