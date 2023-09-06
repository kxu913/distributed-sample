package com.kevin.sample.message.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SampleMessage {
    private int id;
    private String body;
    private String tag;
    private Date createdTime;
}
