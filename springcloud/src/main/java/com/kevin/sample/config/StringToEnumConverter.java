package com.kevin.sample.config;

import com.kevin.sample.constants.QueryType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToEnumConverter implements Converter<String, QueryType> {
    @Override
    public QueryType convert(String source) {

            return QueryType.valueOf(source.toUpperCase());


    }
}
