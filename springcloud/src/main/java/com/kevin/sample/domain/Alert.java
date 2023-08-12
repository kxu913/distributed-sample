package com.kevin.sample.domain;

import com.kevin.sample.constants.AlertStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Alert {

    private int id;
    private String userId;
    private String alertType;
    private double userInput;
    private String symbol;
    private AlertStatus status;
    private Date createdTime;
    private Date updatedTime;

}
