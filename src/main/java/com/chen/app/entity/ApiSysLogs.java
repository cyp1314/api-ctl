package com.chen.app.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ApiSysLogs)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysLogs implements Serializable {
    private static final long serialVersionUID = 526726274599495258L;
    
    private Integer id;
    
    private String appKey;
    
    private String params;
    
    private String returnvalue;
    
    private Long calltime;


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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getReturnvalue() {
        return returnvalue;
    }

    public void setReturnvalue(String returnvalue) {
        this.returnvalue = returnvalue;
    }

    public Long getCalltime() {
        return calltime;
    }

    public void setCalltime(Long calltime) {
        this.calltime = calltime;
    }

}