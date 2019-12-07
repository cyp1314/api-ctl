package com.chen.app.controller;

import cn.hutool.core.util.IdcardUtil;
import com.chen.app.annotation.AccessLimit;
import com.chen.app.annotation.AppKey;
import com.chen.app.annotation.Log;
import com.chen.app.annotation.OverWork;
import com.chen.app.annotation.WhiteList;
import com.chen.app.apidata.IdcarInfo;
import com.chen.app.util.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IdcardController {

    @Log
    @AppKey
    @AccessLimit
    @OverWork
    @GetMapping("/idcard")
    public R idcard(String cardno, String key){
        IdcarInfo idcarInfo = new IdcarInfo();
        if (StringUtils.isEmpty(cardno) && !IdcardUtil.isValidCard(cardno)){
            return R.err_203801();
        }
        idcarInfo.setAge(IdcardUtil.getAgeByIdCard(cardno));
        idcarInfo.setBirth(IdcardUtil.getBirthByIdCard(cardno));
        idcarInfo.setSex(IdcardUtil.getGenderByIdCard(cardno) == 0 ? "女" : "男");
        idcarInfo.setProvince(IdcardUtil.getProvinceByIdCard(cardno));

        Map<String,Object> result = new HashMap<>();
        result.put("result",idcarInfo);
        return R.ok(result);
    }
}
