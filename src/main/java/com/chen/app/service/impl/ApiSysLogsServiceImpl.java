package com.chen.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.app.entity.ApiSysLogs;
import com.chen.app.mapper.ApiSysLogsMapper;
import com.chen.app.service.ApiSysLogsService;
import org.springframework.stereotype.Service;

@Service
public class ApiSysLogsServiceImpl extends ServiceImpl<ApiSysLogsMapper, ApiSysLogs> implements ApiSysLogsService {
}
