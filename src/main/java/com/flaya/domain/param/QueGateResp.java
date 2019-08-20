package com.flaya.domain.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueGateResp implements Serializable {

    private static final long serialVersionUID = -8472112117149069582L;

    private String costtime;
    //    private String Plugin_name;
    private String Register;
    private String PlatformID;
    private Integer Online;
    private String Result;
    private String IPAddr;
    private String Status;
    private String FailReason;
    private String AppVer;
//    private String port;

    @JsonProperty("Register")
    public String getRegister() {
        return Register;
    }

    @JsonProperty("PlatformID")
    public String getPlatformID() {
        return PlatformID;
    }

    @JsonProperty("Online")
    public Integer getOnline() {
        return Online;
    }

    @JsonProperty("Result")
    public String getResult() {
        return Result;
    }

    @JsonProperty("IPAddr")
    public String getIPAddr() {
        return IPAddr;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return Status;
    }

    @JsonProperty("FailReason")
    public String getFailReason() {
        return FailReason;
    }

    @JsonProperty("AppVer")
    public String getAppVer() {
        return AppVer;
    }

    @Override
    public String toString() {
        return "QueGateResp{" +
                "costtime='" + costtime + '\'' +
                ", Register='" + Register + '\'' +
                ", PlatformID='" + PlatformID + '\'' +
                ", Online=" + Online +
                ", Result='" + Result + '\'' +
                ", IPAddr='" + IPAddr + '\'' +
                ", Status='" + Status + '\'' +
                ", FailReason='" + FailReason + '\'' +
                ", AppVer='" + AppVer + '\'' +
//                ", port='" + port + '\'' +
                '}';
    }
}
