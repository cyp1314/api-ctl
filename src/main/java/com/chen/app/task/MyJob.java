package com.chen.app.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.app.entity.ApiCusGood;
import com.chen.app.service.ApiCusGoodService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@JobHandler(value="myJob")
@Component
public class MyJob extends IJobHandler{

    @Autowired
    ApiCusGoodService cusGoodService;


    @Override
    public ReturnT<String> execute(String s) throws Exception {
        Long now = System.currentTimeMillis();
        List<ApiCusGood> list = cusGoodService.list(new QueryWrapper<ApiCusGood>().lt("api_numbers", 0).or().gt("api_endtime", now));

        for (ApiCusGood g:list) {
            int cus_id = g.getCusId();
            String apiName = g.getGoodName();
            XxlJobLogger.log("亲爱的"+cus_id+"客户，您的"+apiName+"已经欠费，请及时充值！" );
            System.out.println("亲爱的"+cus_id+"客户，您的"+apiName+"已经欠费，请及时充值！");

        }
        return SUCCESS;
    }
}
