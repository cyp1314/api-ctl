package com.chen.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.app.entity.ApiSysQps;
import com.chen.app.mapper.ApiSysQpsMapper;
import com.chen.app.service.ApiSysQpsService;
import org.springframework.stereotype.Service;

@Service
public class ApiSysQpsServiceImpl extends ServiceImpl<ApiSysQpsMapper, ApiSysQps> implements ApiSysQpsService {
}
