package com.chen.app.entity;

import java.io.Serializable;

/**
 * 接口价格规格信息(ApiGoodInfo)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiGoodInfo implements Serializable {
    private static final long serialVersionUID = -22997234278165004L;
    
    private Integer id;
    /**
    * 商品编号
    */
    private Integer goodId;
    /**
    * 使用方式
    */
    private Integer chargemodeId;
    /**
    * 价格
    */
    private String moery;
    /**
    * 付费说明
    */
    private String desc;
    /**
    * 使用次数限制
    */
    private Integer numbers;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getChargemodeId() {
        return chargemodeId;
    }

    public void setChargemodeId(Integer chargemodeId) {
        this.chargemodeId = chargemodeId;
    }

    public String getMoery() {
        return moery;
    }

    public void setMoery(String moery) {
        this.moery = moery;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

}