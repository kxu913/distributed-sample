package com.kevin.sample.seckill.service;

import com.alibaba.fastjson.JSONObject;

public interface SecondKillService {

    JSONObject startSecKillV2(long inventoryId);

    String secondKillV2(String userId, long inventoryId);
}
