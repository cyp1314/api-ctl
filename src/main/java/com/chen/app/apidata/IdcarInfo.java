package com.chen.app.apidata;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdcarInfo implements Serializable {
    String birth;
    Integer age;
    String sex;
    String province;
}
