package com.flaya.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Resp implements Serializable {

    private static final long serialVersionUID = 686897169953861511L;

    private String code;
    private String text;
    private Object ext;
    private Object costtime;
//    @JsonProperty("Register")
    private Object Register;
//    @JsonProperty
    private Object PlatformID;
//    @JsonProperty
    private Object Online;
//    @JsonProperty
    private Object Result;

    @JsonProperty("Register")
    public Object getRegister() {
        return Register;
    }

    @JsonProperty("PlatformID")
    public Object getPlatformID() {
        return PlatformID;
    }

    @JsonProperty("Online")
    public Object getOnline() {
        return Online;
    }

    @JsonProperty("Result")
    public Object getResult() {
        return Result;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", ext=" + ext +
                ", costtime=" + costtime +
                ", Register=" + Register +
                ", PlatformID=" + PlatformID +
                ", Online=" + Online +
                ", Result=" + Result +
                '}';
    }
}
