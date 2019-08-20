package com.flaya.controller;

import com.flaya.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/json")
public class JsonController {

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
    public Object read (@RequestParam("str") String str) throws IOException {
        return jsonService.read(str);
    }

    @RequestMapping(value = "/read2", method = {RequestMethod.GET, RequestMethod.POST})
    public Object read2 () throws IOException {
        String str = "{\"IPAddr\":\"180.111.170.248\",\"costtime\":553,\"Register\":0,\"port\":\"38727\",\"PlatformID\":\"5\",\"AppVer\":\"3.0\",\"Online\":1,\"Result\":0}";
        return jsonService.read(str);
    }
}
