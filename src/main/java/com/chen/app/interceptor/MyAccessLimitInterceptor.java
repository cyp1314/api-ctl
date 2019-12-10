package com.chen.app.interceptor;

import com.chen.app.annotation.AccessLimit;
import com.chen.app.constants.Constants;
import com.chen.app.entity.ApiSysQps;
import com.chen.app.util.IPUtil;
import com.chen.app.util.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MyAccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }

            Map ParameterMap = request.getParameterMap();
            Map<String, String> reqMap = new HashMap();
            Set<Map.Entry<String, String[]>> entry = ParameterMap.entrySet();
            Iterator<Map.Entry<String, String[]>> it = entry.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String[]> me = it.next();
                String key = me.getKey();
                String value = me.getValue()[0];
                reqMap.put(key, value);
            }

            // 获取参数中的AppKey
            String appKey = reqMap.get("key");

            String limitQps = (String) redisTemplate.opsForValue().get(Constants.REDIS_KEY_QPS + appKey);
            if (limitQps == null) {
                // 不进行限流除了
                return true;
            }

            ObjectMapper mapper = new ObjectMapper();
            ApiSysQps apiSysQps = mapper.readValue(limitQps, ApiSysQps.class);

            int limit = Integer.parseInt(apiSysQps.getRequestTime());
            int sec = Integer.parseInt(apiSysQps.getSec());

            String key = Constants.REDIS_KEY_QPS_LOG + appKey + IPUtil.getIpAddr(request) + request.getRequestURI();  // ip + 接口地址
            Integer maxLimit = (Integer) redisTemplate.opsForValue().get(key);
            if (maxLimit == null) {
                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
            } else if (maxLimit < limit) {
                redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
            } else {
                output(response, R.err_1012());
                return false;
            }
        }
        return true;
    }

    public void output(HttpServletResponse response, Object object) throws IOException {
        PrintWriter out = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            out.println(mapper.writeValueAsString(object));
        } catch (Exception e) {
            log.error("输出JSON报错!" + e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
