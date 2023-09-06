package com.kevin.sample.message.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${rocketmq.delay-topic}",consumerGroup = "${rocketmq.delay-consumer.group}")
@Slf4j
public class DelayMessageListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("received delay message: {}", s);

    }

}
