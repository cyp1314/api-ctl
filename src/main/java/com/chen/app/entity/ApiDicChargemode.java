package com.chen.app.entity;

import java.io.Serializable;

/**
 * 收费方式(ApiDicChargemode)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiDicChargemode implements Serializable {
    private static final long serialVersionUID = 653863131056184305L;
    
    private Integer id;
    
    private String name;
    
    private String desc;


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

}