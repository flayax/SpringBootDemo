package com.flaya.enums;

/**
 * 错误码枚举
 */
public enum ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    UNKNOWN_DATA(0, "无效记录"),
    SUCCESS(1, "成功"),
    PRIMARY_SCHOOL(100, "上小学"),
    MIDDLE_SCHOOL(101, "上中学")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
