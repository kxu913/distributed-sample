package com.kevin.sample.order.algorithm;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class AtomicIntegerKeyGenerator implements KeyGenerateAlgorithm {

    private final AtomicInteger key = new AtomicInteger(1);
    private Properties props = new Properties();
    @Override
    public Comparable<?> generateKey() {

        return key.addAndGet(1);
    }

    @Override
    public void init(Properties props) {
        this.props = props;

    }

    @Override
    public String getType(){
        return "ATOMIC";

    }
}
