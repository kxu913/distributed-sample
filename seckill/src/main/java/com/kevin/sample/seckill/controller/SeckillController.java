package com.kevin.sample.seckill.controller;


import com.alibaba.fastjson.JSONObject;

import com.kevin.sample.seckill.service.SecondKillService;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@Slf4j
public class SeckillController {

    @Autowired
    private final SecondKillService service;
    @PostMapping("/secondKill/{inventoryId}")
    public String secondKillV2(@RequestHeader("uid")String userId,@PathVariable long inventoryId){
        return service.secondKillV2(userId,inventoryId);

    }
    @PostMapping("/secondKill/start/{inventoryId}")
    @ResponseBody
    public JSONObject startSecKillV2(@PathVariable long inventoryId){
        return service.startSecKillV2(inventoryId);

    }
}
