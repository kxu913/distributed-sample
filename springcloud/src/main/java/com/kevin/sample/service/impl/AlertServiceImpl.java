package com.kevin.sample.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.kevin.sample.domain.Alert;
import com.kevin.sample.mapper.AlertMapper;
import com.kevin.sample.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertMapper mapper;


    @Override
    public List<Alert> getAlerts(String userId) {
        return mapper.getAlerts(userId);
    }

    @Override
    public List<Alert> getAlertsByType(String alertType) {
        return mapper.getAlertsByType(alertType);
    }
}
