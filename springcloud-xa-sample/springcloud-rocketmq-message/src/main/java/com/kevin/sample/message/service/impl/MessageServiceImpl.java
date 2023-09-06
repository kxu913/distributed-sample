package com.kevin.sample.message.service.impl;

import com.kevin.sample.message.domain.SampleMessage;
import com.kevin.sample.message.mapper.MessageMapper;
import com.kevin.sample.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageMapper mapper;

    @Override
    @Transactional
    public int insert(SampleMessage message) {
        return mapper.insert(message);
    }

    @Override
    public int check(int id){
        return mapper.count(id);
    }
}
