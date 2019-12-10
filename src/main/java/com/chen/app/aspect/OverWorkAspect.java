package com.chen.app.aspect;

import com.alibaba.fastjson.JSON;
import com.chen.app.annotation.AccessLimit;
import com.chen.app.mq.MqSender;
import com.chen.app.util.LogAopUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
@Slf4j
public class OverWorkAspect {

    @Autowired
    MqSender mqSender;

    @Pointcut("@annotation(com.chen.app.annotation.OverWork)")
    public void logPointCut() {
    }

    @AfterReturning(returning = "o", pointcut = "logPointCut()")
    public void methodAfterReturing(JoinPoint joinPoint,Object o) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取参数
        Map ParameterMap =  request.getParameterMap();
        Map<String,String> reqMap = new HashMap();
        Set<Map.Entry<String,String[]>> entry = ParameterMap.entrySet();
        Iterator<Map.Entry<String,String[]>> it = entry.iterator();
        while (it.hasNext()){
            Map.Entry<String,String[]>  me = it.next();
            String key = me.getKey();
            String value = me.getValue()[0];
            reqMap.put(key,value);
        }

        String key = reqMap.get("key");

//        log.info("--------------返回内容----------------");
//        log.info("Response内容:" + JSON.toJSONString(o));
//        log.info("--------------返回内容----------------");

        mqSender.send(key);
    }

}
