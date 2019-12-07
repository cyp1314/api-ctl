package com.chen.app.entity;

import java.io.Serializable;

/**
 * (ApiSysGoods)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysGoods implements Serializable {
    private static final long serialVersionUID = 927369883004082719L;
    
    private Integer id;
    
    private String name;
    
    private String desc;
    
    private String address;

    private String apiUri;
    
    private String returnValueType;
    
    private String requestWay;
    
    private String requestCase;
    
    private String remark;
    
    private String requestParmsExplain;
    
    private String returnValueExplain;
    
    private String returnValueExample;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getReturnValueType() {
        return returnValueType;
    }

    public void setReturnValueType(String returnValueType) {
        this.returnValueType = returnValueType;
    }

    public String getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(String requestWay) {
        this.requestWay = requestWay;
    }

    public String getRequestCase() {
        return requestCase;
    }

    public void setRequestCase(String requestCase) {
        this.requestCase = requestCase;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRequestParmsExplain() {
        return requestParmsExplain;
    }

    public void setRequestParmsExplain(String requestParmsExplain) {
        this.requestParmsExplain = requestParmsExplain;
    }

    public String getReturnValueExplain() {
        return returnValueExplain;
    }

    public void setReturnValueExplain(String returnValueExplain) {
        this.returnValueExplain = returnValueExplain;
    }

    public String getReturnValueExample() {
        return returnValueExample;
    }

    public void setReturnValueExample(String returnValueExample) {
        this.returnValueExample = returnValueExample;
    }

}