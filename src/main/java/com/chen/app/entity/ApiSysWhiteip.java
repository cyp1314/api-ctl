package com.chen.app.entity;

import java.io.Serializable;

/**
 * 接口白名单信息记录表(ApiSysWhiteip)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysWhiteip implements Serializable {
    private static final long serialVersionUID = 693676574610310246L;
    /**
    * 编号
    */
    private Integer id;
    /**
    * app_key
    */
    private String appKey;
    /**
    * 白名单ip
    */
    private String wIp;


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

    public String getWIp() {
        return wIp;
    }

    public void setWIp(String wIp) {
        this.wIp = wIp;
    }

}