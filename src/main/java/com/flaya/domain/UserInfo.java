package com.flaya.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 2243328918926498002L;

    private String name;

    private String phone;

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
