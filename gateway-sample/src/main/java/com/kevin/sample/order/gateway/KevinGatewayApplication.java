package com.kevin.sample.order.gateway;

import com.alibaba.cloud.sentinel.gateway.ConfigConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class KevinGatewayApplication {
    public static void main(String[] args) {
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(KevinGatewayApplication.class, args);
    }
}
