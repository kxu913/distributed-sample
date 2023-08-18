package com.kevin.sample.inventory.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

public class StatusAlgorithm implements StandardShardingAlgorithm {
    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        if(shardingValue.getValue().toString().equalsIgnoreCase("ACTIVE")){
            return "ds0";
        }else{
            return "ds1";
        }
    }

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        throw new UnsupportedOperationException("StatusAlgorithm doesn't support this type.");
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public String getType(){
        return "Status";
    }

    @Override
    public void init(Properties props) {

    }
}
