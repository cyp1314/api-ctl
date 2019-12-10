package com.chen.app.controller;

import cn.hutool.core.util.IdcardUtil;
import com.chen.app.annotation.AccessLimit;
import com.chen.app.annotation.AppKey;
import com.chen.app.annotation.Log;
import com.chen.app.annotation.OverWork;
import com.chen.app.util.ImageBase64;
import com.chen.app.util.R;
import com.chen.app.util.SSLSocketClient;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class FaceController {

    public static final String FACE = "https://verifyservice.110passport.com:7000/service/900101";
    public static final MediaType JSONTO = MediaType.parse("application/json;charset=utf-8");

    @Log
    @AppKey
    @AccessLimit
    @OverWork
    @PostMapping("/face")
    public R result(String name,String idnumber,String image,String key) {

        if (StringUtils.isEmpty(name)){
            return R.err_201902();
        }
        if (StringUtils.isEmpty(idnumber)){
            return R.err_201903();
        }
        if (!IdcardUtil.isValidCard(idnumber)){
            return R.err_201904();
        }
        // 大于2M
        Integer integer = ImageBase64.imageSize(image);
        if (integer > 2097152){
            return R.err_201901();
        }


        OkHttpClient mHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .build();

        HashMap<String, Object> map = new HashMap<>();
        map.put("cmd", "faceRecognition");
        map.put("name", name);
        map.put("idnumber", idnumber);
        map.put("image", image);
        map.put("imagetype", 0);
        map.put("sendTime", Long.toString(System.currentTimeMillis()));
        map.put("terminalNo", System.currentTimeMillis() + "");

        Gson gson = new Gson();
        String data1 = gson.toJson(map);

        RequestBody body = RequestBody.create(JSONTO, data1);
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new URL(FACE))
                    .post(body)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Response response = null;
        try {
            response = mHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return R.err_201905();
        }
        String responseData = null;
        try {
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return R.err_201905();
        }

        Map<String,Object> result = new HashMap<>();
        result.put("result",responseData);
        return R.ok(result);
    }
}

//{
//        "result": "{\"Score\":-1,\"ResponseCode\":420,\"ResponseText\":\"图片编码错误\",\"Result\":0,\"ResultText\":null}",
//        "reason": "成功的返回",
//        "resultcode": 200
//        }

//{
//        "result": "{\"Score\":91,\"ResponseCode\":100,\"ResponseText\":\"接口调用成功\",\"Result\":1,\"ResultText\":\"判定为同一人\"}",
//        "reason": "成功的返回",
//        "resultcode": 200
//        }