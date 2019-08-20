package com.flaya.domain.param;

import com.flaya.domain.CyberInform;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CloudParam implements Serializable {

    private static final long serialVersionUID = -7108071545971473321L;

    List<CyberInform> cyberInforms;

    @Override
    public String toString() {
        return "cloudParam{" +
                "cyberInforms=" + cyberInforms +
                '}';
    }
}
