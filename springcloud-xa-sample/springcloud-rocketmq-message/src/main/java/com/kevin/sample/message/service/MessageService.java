package com.kevin.sample.message.service;

import com.kevin.sample.message.domain.SampleMessage;

public interface MessageService {

    public int insert(SampleMessage message);

    public int check(int id);
}
