package com.kevin.sample.service;

import com.kevin.sample.domain.Alert;

import java.util.List;

public interface AlertService {

    public List<Alert> getAlerts(String userId);
     List<Alert> getAlertsByType(String alertType);

}
