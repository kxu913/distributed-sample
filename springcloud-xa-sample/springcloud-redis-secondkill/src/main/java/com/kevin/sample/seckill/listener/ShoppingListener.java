package com.kevin.sample.seckill.listener;

import com.kevin.sample.seckill.domain.Order;
import com.kevin.sample.seckill.domain.Shopping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
@RocketMQTransactionListener
@Slf4j
@AllArgsConstructor
public class ShoppingListener implements RocketMQLocalTransactionListener {

    private final StringRedisTemplate stringRedisTemplate;

    private final RestTemplate restTemplate;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.UNKNOWN;
        log.info("start send message with {}/{}", msg.getHeaders().get("user-id"), msg.getHeaders().get("inventory-id"));
        try {
            //TODO here need check whether order is exist in db then do update.

            int userId = Integer.parseInt(msg.getHeaders().get("user-id").toString());
            long inventoryId = Long.parseLong(msg.getHeaders().get("inventory-id").toString());
            HttpHeaders headers = new HttpHeaders();
            log.info("message {} to redis.", msg.getHeaders().get("xid"));
            Shopping shopping = new Shopping();
            shopping.setPcs(1);
            shopping.setOrder(new Order().buildSeckillOrder().withUserId(userId).withInventoryId(inventoryId));
            shopping.setInventoryId(inventoryId);
            HttpEntity<Shopping> entity = new HttpEntity<>(shopping, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity("http://shopping/shopping", entity, Map.class);
            if (response.getStatusCode().value() == 200) {
                log.info("insert message {} to redis.", msg.getHeaders().get("xid"));
                stringRedisTemplate.opsForValue().set(Objects.requireNonNull(msg.getHeaders().get("xid")).toString(), "1");
                return RocketMQLocalTransactionState.COMMIT;
            } else {
                log.warn("insert order failed.");
            }

        } catch (Exception e) {
            log.error(e.getMessage());

        }
        return result;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("double check...{}", msg.getHeaders().get("xid"));
        String xid = stringRedisTemplate.opsForValue().get(Objects.requireNonNull(msg.getHeaders().get("xid")).toString());
        if ("1".equals(xid)) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
