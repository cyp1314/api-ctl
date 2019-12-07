package com.chen.app.constants;


public enum PaymentType {
    FREE("免费",(short)1),
    LIMITED_TIME_FREE("限时免费",(short)2);

    private short code;
    private String name;

    PaymentType(String name,short code) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Short code,String name) {
        for (PaymentType c : PaymentType.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
