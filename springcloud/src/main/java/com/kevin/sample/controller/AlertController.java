package com.kevin.sample.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.kevin.sample.constants.QueryType;
import com.kevin.sample.domain.Alert;
import com.kevin.sample.service.AlertService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@Slf4j
public class AlertController {

    private final AlertService service;

    @GetMapping("/alerts/{type}/{value}")
    @ResponseBody
    @SentinelResource(value = "retrieveAlerts")
    public List<Alert> getAlerts(@PathVariable("type") QueryType type, @PathVariable("value") String value){
        log.info(type+value);
        if (Objects.requireNonNull(type) == QueryType.TYPE) {
            return service.getAlertsByType(value);
        }
        return service.getAlerts(value);
    }

}
