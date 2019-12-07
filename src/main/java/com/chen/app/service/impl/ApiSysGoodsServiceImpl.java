package com.chen.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.app.entity.ApiSysGoods;
import com.chen.app.mapper.ApiSysGoodsMapper;
import com.chen.app.service.ApiSysGoodsService;
import org.springframework.stereotype.Service;

@Service
public class ApiSysGoodsServiceImpl extends ServiceImpl<ApiSysGoodsMapper, ApiSysGoods> implements ApiSysGoodsService {
}
