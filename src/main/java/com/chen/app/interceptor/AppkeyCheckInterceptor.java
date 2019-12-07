package com.chen.app.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.app.annotation.AccessLimit;
import com.chen.app.annotation.AppKey;
import com.chen.app.constants.Constants;
import com.chen.app.data.ApiCusGoodData;
import com.chen.app.entity.ApiCusGood;
import com.chen.app.entity.ApiSysGoods;
import com.chen.app.entity.ApiSysWhiteip;
import com.chen.app.service.ApiCusGoodService;
import com.chen.app.service.ApiSysGoodsService;
import com.chen.app.service.ApiSysWhiteipService;
import com.chen.app.util.IPUtil;
import com.chen.app.util.R;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.CellEditor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 1.判断key是否存在
 * 2.判断key是否开通了此接口
 * 3.判断key是否可用
 * 4.判断keu是否存在白名单限制
 * >1.判断接口是否停用
 * >2.根据接口收费模式判断是否可用
 */
@Component
@Slf4j
public class AppkeyCheckInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ApiSysWhiteipService whiteipService;

    @Autowired
    ApiSysGoodsService goodsService;

    @Autowired
    ApiCusGoodService cusGoodService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AppKey.class)) {
                return true;
            }
            AppKey appKey = method.getAnnotation(AppKey.class);
            if (appKey == null) {
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

            ObjectMapper mapper = new ObjectMapper();


            // 获取参数中的AppKey
            String key = reqMap.get("key");
            if (StringUtils.isEmpty(key)) {
                output(response, R.err_1001());
                return false;
            }


//            ApiCusGood cusGood = cusGoodService.getOne(new QueryWrapper<ApiCusGood>().eq("app_key", key));
//
//            if (cusGood == null){
//                output(response,R.err_1001());
//                return false;
//            }

//            Boolean keyHas = redisTemplate.hasKey(key);
//            if (!keyHas){
//                output(response,R.err_1001());
//                return false;
//            }

            String data = (String) redisTemplate.opsForValue().get(key);
            if (data == null) {
                output(response, R.err_1001());
                return false;
            }

            ApiCusGoodData cusGoodData = mapper.readValue(data, ApiCusGoodData.class);

            // key和接口是否匹配
            String requestURI = request.getRequestURI();
            String appUri = cusGoodData.getAppUri();
            if (!requestURI.equals(appUri)) {
                output(response, R.err_1002());
                return false;
            }


            // 是否有效期内（剩余次数和在使用时间段内）
            byte type = (byte) cusGoodData.getGoodType();
            boolean checked = false;
            switch (type) {
                case 0:
                    if (cusGoodData.getApiNumbers() > 0) {
                        checked = true;
                    }
                    break;
                case 1:
                    if (cusGoodData.getApiNumbers() > 0) {
                        checked = true;
                    }
                    break;
                case 2:
                    checked = true;
                    break;
                default:
                    long now = System.currentTimeMillis();
                    if (now > cusGoodData.getApiStarttime() && now < cusGoodData.getApiEndtime()) {
                        checked = true;
                    }
            }

            if (!checked) {
                output(response, R.err_1007());
                return false;
            }


            // 白名单
            String w_ips = (String) redisTemplate.opsForValue().get(Constants.REDIS_KEY_WHITEIP + key);
            if (w_ips != null) {
                String req_ip = IPUtil.getIpAddr(request);
                ApiSysWhiteip apiSysWhiteip = mapper.readValue(w_ips, ApiSysWhiteip.class);
                String[] split_ip = apiSysWhiteip.getWIp().split(",");
                /**
                 * Arrays.asList(args).contains(str);
                 * sets.contains(str);
                 * Arrays.binarySearch(args, str);
                 * loop 效率最高
                 */
                for (String ip : split_ip) {
                    if (!ip.equals(req_ip)) {
                        output(response, R.err_1005());
                        return false;
                    }
                }
            }

            return true;
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
