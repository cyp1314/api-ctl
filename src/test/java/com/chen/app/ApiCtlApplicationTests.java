package com.chen.app;

import com.chen.app.entity.ApiSysGoods;
import com.chen.app.service.ApiSysGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ApiCtlApplicationTests {

    @Resource
    RedisTemplate<String,Integer> redisTemplate;

    @Autowired
    ApiSysGoodsService apiSysGoodsService;

    @Test
    void redis() {
        redisTemplate.opsForValue().set("a",1);

        Integer a = (Integer) redisTemplate.opsForValue().get("a");
        System.out.println(a);
    }

    @Test
    void testMybatis(){
        ApiSysGoods apiSysGoods = new ApiSysGoods();
        apiSysGoods.setName("测试");
        apiSysGoodsService.save(apiSysGoods);
    }
}
