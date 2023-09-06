package com.kevin.sample.order.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

public class MyShardingAlgorithm implements StandardShardingAlgorithm {
    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties props) {

    }

    @Override
    public String getType(){
        return "My";
    }

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        if(shardingValue.getValue().toString().equalsIgnoreCase("kevin")){
            return "t_order0";
        }else{
            return "t_order1";
        }
    }

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        throw new UnsupportedOperationException("My algorithm doesn't support this type.");
    }
}
