package com.chen.app.data;

import java.io.Serializable;

public class ApiCusGoodData implements Serializable {

    private String key;
    private String appUri;

    private int goodType;
    private int apiNumbers;
    private Long apiStarttime;
    private Long apiEndtime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppUri() {
        return appUri;
    }

    public void setAppUri(String appUri) {
        this.appUri = appUri;
    }

    public int getGoodType() {
        return goodType;
    }

    public void setGoodType(int goodType) {
        this.goodType = goodType;
    }

    public int getApiNumbers() {
        return apiNumbers;
    }

    public void setApiNumbers(int apiNumbers) {
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

    public ApiCusGoodData() {
    }

    public ApiCusGoodData(String key, int goodType, int apiNumbers, Long apiStarttime, Long apiEndtime) {
        this.key = key;
        this.goodType = goodType;
        this.apiNumbers = apiNumbers;
        this.apiStarttime = apiStarttime;
        this.apiEndtime = apiEndtime;
    }
}
