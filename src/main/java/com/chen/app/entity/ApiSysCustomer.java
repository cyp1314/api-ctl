package com.chen.app.entity;

import java.io.Serializable;

/**
 * (ApiSysCustomer)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysCustomer implements Serializable {
    private static final long serialVersionUID = -72682964706099087L;
    /**
    * 用户编号
    */
    private Integer id;
    /**
    * 用户名
    */
    private String name;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 用户类型(个人,企业)
    */
    private String type;
    /**
    * 认证状态(未认证,已认证)
    */
    private String status;
    /**
    * 绑定邮箱
    */
    private String email;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}