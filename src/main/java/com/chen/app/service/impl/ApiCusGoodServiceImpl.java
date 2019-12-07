package com.chen.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.app.entity.ApiCusGood;
import com.chen.app.mapper.ApiCusGoodMapper;
import com.chen.app.service.ApiCusGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCusGoodServiceImpl extends ServiceImpl<ApiCusGoodMapper, ApiCusGood> implements ApiCusGoodService {

    @Autowired
    ApiCusGoodMapper cusGoodMapper;

    @Override
    public List<String> findAllKeys() {
        return this.cusGoodMapper.findAllKeys();
    }

    @Override
    public int updateApiNumers(String key,Integer numbers) {
        return cusGoodMapper.updateApiNumers(key,numbers);
    }
}
