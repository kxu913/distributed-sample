package com.kevin.sample.message.controller;

import com.google.common.base.Strings;

import com.kevin.sample.message.domain.SampleMessage;
import com.kevin.sample.message.listener.MessageListener;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@RestController
@Slf4j
public class MessageController {

    @Value("${rocketmq.topic}")
    private String topic;

    @Value("${rocketmq.delay-topic}")
    private String delayTopic;
    @Value("${rocketmq.tx-topic}")
    private String txTopic;
    @Value("${rocketmq.consumer.group}")
    private String consumerGroup;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private MessageListener listener;


    @PostMapping("/message")
    @ResponseBody
    public SendResult send(@RequestBody SampleMessage message) {
        String destination = Strings.isNullOrEmpty(message.getTag()) ? topic : topic + ":" + message.getTag();
        return rocketMQTemplate.syncSend(destination, MessageBuilder.withPayload(message.getBody()).build(), 10000);
    }

    @PostMapping("/message/delay")
    @ResponseBody
    public SendResult sendDelayMessage(@RequestBody SampleMessage message) {
        String destination = Strings.isNullOrEmpty(message.getTag()) ? delayTopic : delayTopic + ":" + message.getTag();
        SendResult sr = rocketMQTemplate.syncSendDelayTimeMills(destination, MessageBuilder.withPayload(message.getBody()).build(), 6000);
        log.info("sent a delay message.");
        return sr;
    }

    @PostMapping("/message/tx")
    @ResponseBody
    public SendResult sendTxMessage(@RequestBody SampleMessage message) {
        String destination = Strings.isNullOrEmpty(message.getTag()) ? txTopic : txTopic + ":" + message.getTag();
        message.setCreatedTime(new Date());
        SendResult sr = rocketMQTemplate.sendMessageInTransaction(destination, MessageBuilder.withPayload(message).setHeader("msg-id", message.getId()).build(), message.getBody());
        log.info("sent a tx message.");
        return sr;
    }

    @GetMapping(value = "message", produces = "text/event-stream;charset=UTF-8")
    public void receive(HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        PrintWriter pw = response.getWriter();
        listener.register(pw);
        pw.write("data:registered. \r\n");
        pw.flush();
        while (true) {

        }

    }
}
