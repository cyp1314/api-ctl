package com.chen.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WhiteListAspect {

    @Pointcut("@annotation(com.chen.app.annotation.WhiteList)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String remoteAddr = request.getRemoteAddr();

        Object result = point.proceed();

        System.out.println("客户端IP："+ remoteAddr);

        return result;
    }

    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) throws Exception{
        System.out.println("进入aop-----------------------");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            System.out.println("@@"+joinPoint.getArgs()[i]);
        }
        System.out.println(joinPoint.getSignature().getName());
    }

    @After("logPointCut()")
    public void after(JoinPoint joinPoint){
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            System.out.println(joinPoint.getArgs()[i]);
        }
        System.out.println(joinPoint.getSignature().getName());

    }

}
