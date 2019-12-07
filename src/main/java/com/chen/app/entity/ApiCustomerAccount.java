package com.chen.app.entity;

import java.io.Serializable;

/**
 * 账户余额(ApiCustomerAccount)实体类
 *
 * @author makejava
 * @since 2019-12-06 18:06:39
 */
public class ApiCustomerAccount implements Serializable {
    private static final long serialVersionUID = 739640634427261927L;
    /**
    * 编号
    */
    private Integer id;
    /**
    * 用户编号
    */
    private Integer cusId;
    /**
    * 余额,单位分
    */
    private Integer monery;
    /**
    * 冻结资金，单位分
    */
    private Integer freezeMonery;
    /**
    * 余额预警
    */
    private String status;


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

    public Integer getMonery() {
        return monery;
    }

    public void setMonery(Integer monery) {
        this.monery = monery;
    }

    public Integer getFreezeMonery() {
        return freezeMonery;
    }

    public void setFreezeMonery(Integer freezeMonery) {
        this.freezeMonery = freezeMonery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}