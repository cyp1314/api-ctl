package com.chen.app.entity;

import java.io.Serializable;

/**
 * 接口白名单信息记录表(ApiSysQps)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysQps implements Serializable {
    private static final long serialVersionUID = 509876080078780982L;
    /**
    * 编号
    */
    private Integer id;
    /**
    * app_key
    */
    private String appKey;
    /**
    * 请求次数的指定时间范围
    */
    private String sec;
    /**
    * 指定second 时间内 API请求次数
    */
    private String requestTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}