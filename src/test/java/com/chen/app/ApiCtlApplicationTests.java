package com.chen.app;

import com.chen.app.data.InlineResource;
import com.chen.app.email.SendEmailHelper;
import com.chen.app.entity.ApiSysGoods;
import com.chen.app.service.ApiSysGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApiCtlApplicationTests {

    @Resource
    RedisTemplate<String,Integer> redisTemplate;

    @Autowired
    ApiSysGoodsService apiSysGoodsService;

    @Autowired
    SendEmailHelper sendEmailHelper;

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


    @Test
    public void snedHtmlMail() {
        String html= "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<meta charset=\"UTF-8\">\r\n" +
                "<title>Insert title here</title>\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "	<font color=\"red\">发送html</font>\r\n" +
                "</body>\r\n" +
                "</html>";
        sendEmailHelper.sendHtmlMail("1165904938@qq.com", "这是一个测试邮件", html);
    }


    @Test
    public void sendInlineResourceMail() throws MessagingException {
        String html= "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<meta charset=\"UTF-8\">\r\n" +
                "<title>Insert title here</title>\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "<img src=\"cid:image1\"/> "+
                "<img src=\"cid:image2\"/> "+
                "	<font color=\"red\">发送html</font>\r\n" +
                "</body>\r\n" +
                "</html>";
        List<InlineResource> list = new ArrayList<InlineResource>();
        String path = ApiCtlApplicationTests.class.getClassLoader().getResource("image.jpg").getPath();

        InlineResource resource = new InlineResource("image1",path);
        InlineResource resource2 = new InlineResource("image2",path);

        list.add(resource2);
        list.add(resource);
        sendEmailHelper.sendInlineResourceMail("1165904938@qq.com", "这是一个测试邮件", html,list);
    }
}
