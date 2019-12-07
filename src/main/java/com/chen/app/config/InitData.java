package com.chen.app.config;

import com.chen.app.constants.Constants;
import com.chen.app.data.ApiCusGoodData;
import com.chen.app.entity.ApiCusGood;
import com.chen.app.entity.ApiSysQps;
import com.chen.app.entity.ApiSysWhiteip;
import com.chen.app.service.ApiCusGoodService;
import com.chen.app.service.ApiSysQpsService;
import com.chen.app.service.ApiSysWhiteipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements ApplicationRunner {

    @Autowired
    ApiCusGoodService cusGoodService;

    @Autowired
    ApiSysWhiteipService whiteipService;

    @Autowired
    ApiSysQpsService qpsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ApiCusGood> apiCusGoodList = cusGoodService.list();
        ApiCusGoodData cusGoodData = new ApiCusGoodData();
        for (ApiCusGood g:apiCusGoodList) {
            cusGoodData.setKey(g.getAppKey());
            cusGoodData.setAppUri(g.getAppUri());
            cusGoodData.setGoodType(g.getGoodType());
            cusGoodData.setApiNumbers(g.getApiNumbers());
            cusGoodData.setApiStarttime(g.getApiStarttime());
            cusGoodData.setApiEndtime(g.getApiEndtime());
            redisTemplate.opsForValue().set(g.getAppKey(),mapper.writeValueAsString(cusGoodData));
        }

        List<ApiSysWhiteip> apiSysWhiteipList = whiteipService.list();
        ApiSysWhiteip apiSysWhiteip = new ApiSysWhiteip();
        for (ApiSysWhiteip g:apiSysWhiteipList) {
            apiSysWhiteip.setAppKey(g.getAppKey());
            apiSysWhiteip.setWIp(g.getWIp());
            redisTemplate.opsForValue().set(Constants.REDIS_KEY_WHITEIP + g.getAppKey(),mapper.writeValueAsString(apiSysWhiteip));
        }

        List<ApiSysQps> apiSysQpsList = qpsService.list();
        ApiSysQps apiSysQps = new ApiSysQps();
        for (ApiSysQps g:apiSysQpsList) {
            apiSysQps.setAppKey(g.getAppKey());
            apiSysQps.setRequestTime(g.getRequestTime());
            apiSysQps.setSec(g.getSec());
            redisTemplate.opsForValue().set(Constants.REDIS_KEY_QPS + g.getAppKey(),mapper.writeValueAsString(apiSysQps));
        }
    }
}
