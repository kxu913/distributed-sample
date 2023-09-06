package com.kevin.sample.message.listener;

import com.kevin.sample.message.domain.SampleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${rocketmq.tx-topic}",consumerGroup = "${rocketmq.tx-consumer.group}")
@Slf4j
public class TransactionMessageListener implements RocketMQListener<SampleMessage> {
    @Override
    public void onMessage(SampleMessage s) {
        log.info("received tx message: {}", s.getBody());

    }

}
