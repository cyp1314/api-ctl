package com.chen.app.config;

import com.chen.app.annotation.AppKey;
import com.chen.app.interceptor.AppkeyCheckInterceptor;
import com.chen.app.interceptor.MyAccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    private MyAccessLimitInterceptor myAccessLimitInterceptor;

    @Autowired
    private AppkeyCheckInterceptor appkeyCheckInterceptor;
    /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // order 执行顺序，越小越先执行
        registry.addInterceptor(appkeyCheckInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/login.html");

        registry.addInterceptor(myAccessLimitInterceptor)
                .order(10)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/login.html");
    }
}

