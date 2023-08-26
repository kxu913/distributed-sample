package com.kevin.sample.message.listener;

import com.google.gson.Gson;
import com.kevin.sample.domain.SampleMessage;
import com.kevin.sample.message.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQTransactionListener
@Slf4j
@AllArgsConstructor
public class TxTransactionListener implements RocketMQLocalTransactionListener {
    private final MessageService service;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        Gson gson = new Gson();
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.UNKNOWN;
        try {
            String msgStr = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            SampleMessage message = gson.fromJson(msgStr, SampleMessage.class);
            service.insert(message);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error(e.getMessage());

        }
        return result;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("txid is {}", msg.getHeaders().get("id"));
        int id = Integer.parseInt(msg.getHeaders().get("msg-id").toString());
        int c = service.check(id);
        log.info("metrics of {}-{}", id, c);
        if (c > 0) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
