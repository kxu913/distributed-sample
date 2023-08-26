package com.kevin.sample.message.listener;

import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "${rocketmq.topic}",consumerGroup = "${rocketmq.consumer.group}")
@Slf4j
public class MessageListener implements RocketMQListener<String> {

    private final List<PrintWriter> pws;

    public MessageListener(){
        this.pws = new ArrayList<>();
    }
    @Override
    public void onMessage(String s) {
        log.info("received message: {}", s);
        log.info("pws {}", pws.size());
        pws.forEach(pw->{
            StringBuffer sb = new StringBuffer("data:");
            sb.append(s);
            sb.append("\r\n");
            pw.write(sb.toString());
            pw.flush();
        });
    }

    public void register(PrintWriter pw){
        this.pws.add(pw);
    }

}
