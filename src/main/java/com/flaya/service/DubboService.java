package com.flaya.service;

import com.egame.network.dao.api.INetworkDaoService;
import com.flaya.dubbo.service.IDemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

//@Service
public class DubboService {

    @Reference(version = "1.0.0")
    private IDemoService demoService;

    @Reference(version = "1.1.0")
    private INetworkDaoService networkDaoService;

    public void pay(){
        System.out.println("******democonsumer被访问******");
        demoService.pay("333");
        System.out.println(demoService.pay("333"));
    }

    public String testNetworkDao(String s) {
        return networkDaoService.testDao(s);
    }
}
