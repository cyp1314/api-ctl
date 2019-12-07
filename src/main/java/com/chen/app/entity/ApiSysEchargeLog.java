package com.chen.app.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 充值明细(ApiSysEchargeLog)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:40
 */
public class ApiSysEchargeLog implements Serializable {
    private static final long serialVersionUID = 855653903918165476L;
    /**
    * 序号
    */
    private Integer id;
    /**
    * 用户编号
    */
    private Integer cusId;
    /**
    * 订单
    */
    private Integer orderId;
    /**
    * 金额
    */
    private String monery;
    /**
    * 名称
    */
    private String goodName;
    /**
    * 购买时间
    */
    private Long createTime;


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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMonery() {
        return monery;
    }

    public void setMonery(String monery) {
        this.monery = monery;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}