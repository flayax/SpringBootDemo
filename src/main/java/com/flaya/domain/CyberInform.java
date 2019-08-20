package com.flaya.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CyberInform implements Serializable {


    private static final long serialVersionUID = -5620316536792990481L;


    private Integer appId;

    private String streamSessionId;

    private Integer eventType;

    private String startTime;

    private String stopTime;

    private Integer returnCode;

    private String operateUserId;

    private List<String> ips;

    private List<UserInfo> userInfos;

    @Override
    public String toString() {
        return "CyberInform{" +
                "appId=" + appId +
                ", streamSessionId='" + streamSessionId + '\'' +
                ", eventType=" + eventType +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", returnCode=" + returnCode +
                ", operateUserId='" + operateUserId + '\'' +
                ", ips=" + ips +
                ", userInfos=" + userInfos +
                '}';
    }
}