package com.flaya.domain.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogReq implements Serializable {

    private static final long serialVersionUID = -3865026960818635249L;

    private String timeStamp; // 心跳时间
    private String username; // 宽带账号
    private String macAddress; // 网关地址
    private String event; // 插件事件
    private String eventResult; // 事件结果
    private String errorCode; // 错误代码
    private String version; // 插件版本号

    public LogReq() {
    }

    @Override
    public String toString() {
        return "LogReq{" +
                "timeStamp='" + timeStamp + '\'' +
                ", username='" + username + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", event='" + event + '\'' +
                ", eventResult='" + eventResult + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
