package com.flaya.domain.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 本平台接口通用响应参数
 */
@Data
public class CommonResp implements Serializable {

    private static final long serialVersionUID = 7287990460636322705L;

    private Integer code;
    private String text;
    private Object ext;

    public CommonResp() {
    }

    public CommonResp(Integer code, String text, Object ext) {
        this.code = code;
        this.text = text;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "code=" + code +
                ", text='" + text + '\'' +
                ", ext=" + ext +
                '}';
    }
}
