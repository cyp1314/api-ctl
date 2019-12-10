package com.chen.app.aspect;

import com.alibaba.fastjson.JSON;
import com.chen.app.entity.ApiSysLogs;
import com.chen.app.mq.MqLogSender;
import com.chen.app.util.LogAopUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    MqLogSender logSender;

    @Pointcut("@annotation(com.chen.app.annotation.Log)")
    public void logPointCut() {
    }

    // 请求method前打印内容
    @Before(value = "logPointCut()")
    public void methodBefore(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 打印请求内容
//        log.info("===============请求内容===============");
//        log.info("请求地址:" + request.getRequestURL().toString());
//        log.info("请求方式:" + request.getMethod());
//        log.info("请求类方法:" + joinPoint.getSignature());
//        log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));

        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); // 获取方法名称
        // 获取参数名称和值
        StringBuffer sb = LogAopUtil.getNameAndArgs(this.getClass(), clazzName, methodName, args);
//        log.info("请求类方法参数名称和值：" + sb);
//        log.info("===============请求内容===============");
    }


//    @Around("logPointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        long beginTime = System.currentTimeMillis();
//        //执行方法
//        Object result = point.proceed();
//        //执行时长(毫秒)
//        long time = System.currentTimeMillis() - beginTime;
//
//        //保存日志
//        System.out.println("执行总耗时："+ time + "毫秒");
//
//        return result;
//    }

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
        ApiSysLogs apiSysLogs = new ApiSysLogs();
        apiSysLogs.setAppKey(key);
        apiSysLogs.setParams(JSON.toJSONString(reqMap));
        apiSysLogs.setReturnvalue(JSON.toJSONString(o));
        apiSysLogs.setCalltime(System.currentTimeMillis());
        logSender.send(JSON.toJSONString(apiSysLogs));
    }

}
