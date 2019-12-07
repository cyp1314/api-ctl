package com.chen.app.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户接口信息表(ApiCusGood)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:39
 */
public class ApiCusGood implements Serializable {
    private static final long serialVersionUID = -76102743195336913L;
    /**
    * 编号
    */
    private Integer id;
    /**
    * 用户编号
    */
    private Integer cusId;
    /**
    * 接口编号
    */
    private String goodId;
    /**
    * 接口名称
    */
    private String goodName;
    /**
    * 获取方式(1.免费，2.试用，3.包年，4.包月)
    */
    private Integer goodType;
    /**
    * app_key
    */
    private String appKey;

    private String appUri;
    /**
    * 申请时间
    */
    private Long createTime;
    /**
    * 接口剩余次数
    */
    private Integer apiNumbers;
    /**
    * 接口开始计费时间
    */
    private Long apiStarttime;
    /**
    * 接口结束计费时间
    */
    private Long apiEndtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodType() {
        return goodType;
    }

    public void setGoodType(Integer goodType) {
        this.goodType = goodType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppUri() {
        return appUri;
    }

    public void setAppUri(String appUri) {
        this.appUri = appUri;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getApiNumbers() {
        return apiNumbers;
    }

    public void setApiNumbers(Integer apiNumbers) {
        this.apiNumbers = apiNumbers;
    }

    public Long getApiStarttime() {
        return apiStarttime;
    }

    public void setApiStarttime(Long apiStarttime) {
        this.apiStarttime = apiStarttime;
    }

    public Long getApiEndtime() {
        return apiEndtime;
    }

    public void setApiEndtime(Long apiEndtime) {
        this.apiEndtime = apiEndtime;
    }

}