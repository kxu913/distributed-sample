package com.kevin.sample.message.service;

import com.kevin.sample.domain.SampleMessage;

public interface MessageService {

    public int insert(SampleMessage message);

    public int check(int id);
}
