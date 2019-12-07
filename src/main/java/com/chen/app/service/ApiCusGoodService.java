package com.chen.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.app.entity.ApiCusGood;

import java.util.List;

public interface ApiCusGoodService extends IService<ApiCusGood> {
    List<String> findAllKeys();

    int updateApiNumers(String key,Integer numbers);
}
