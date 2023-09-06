package com.kevin.sample.uim;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication

public class UIMApplication {

    public static void main(String[] args) {
        SpringApplication.run(UIMApplication.class);
    }
}
