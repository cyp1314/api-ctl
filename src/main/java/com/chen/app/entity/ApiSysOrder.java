package com.chen.app.entity;

import java.io.Serializable;

/**
 * 订单信息(ApiSysOrder)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysOrder implements Serializable {
    private static final long serialVersionUID = -33832362589588171L;
    
    private Integer id;
    /**
    * 订单编号
    */
    private Integer orderId;
    /**
    * 接口编号
    */
    private String goodId;
    /**
    * 接口名称
    */
    private String goodName;
    /**
    * 金额
    */
    private String money;
    /**
    * 状态
    */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}