package com.chen.app.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    //指定second 时间内 API请求次数
    int limit() default 5;

    // 请求次数的指定时间范围  秒数(redis数据过期时间)
    int sec() default 60;
}
