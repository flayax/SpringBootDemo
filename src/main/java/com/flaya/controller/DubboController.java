package com.flaya.controller;

import com.flaya.dubbo.service.IDemoService;
import com.flaya.service.DubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


//@RestController
public class DubboController {

    @Autowired
    private DubboService dubboService;

    @GetMapping("/pay")
    public void pay (){
        dubboService.pay();
    }

    @RequestMapping(value = "/net_dao", method = {RequestMethod.GET, RequestMethod.POST})
    public String testNetworkDao(@RequestParam("str") String s) {
        return dubboService.testNetworkDao(s);
    }

}
