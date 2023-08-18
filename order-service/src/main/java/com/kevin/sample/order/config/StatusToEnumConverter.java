package com.kevin.sample.order.config;


import com.kevin.sample.domain.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StatusToEnumConverter<T> implements Converter<String, OrderStatus> {
    @Override
    public OrderStatus convert(String source) {

            return OrderStatus.valueOf(source.toUpperCase());


    }
}
