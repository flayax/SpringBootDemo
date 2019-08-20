package com.flaya.controller;

import com.flaya.domain.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Slf4j
@RestController
//@EnableAutoConfiguration
@RequestMapping("/spring_boot/rest")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public Object hello(){
//        String url = "http://openapitest.189cube.com/http://openapitest.189cube.com/abilitysupport?ProvinceID=3&AppID=1000000253048925&OperateID=GET_GATEWAY_STATUS&Secret=cdbf9e272abce888&MAC=B4FBF91BE76A";
        String url = "http://openapitest.189cube.com/abilitysupport?ProvinceID={ProvinceID}&AppID={AppID}&OperateID={OperateID}&Secret={Secret}&MAC={MAC}";
//        String url = "http://192.168.251.52:8102/api/v2/egame/iot/package/balance.json?channel_code={channel_code}";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json");
//        headers.setContentType(type);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ProvinceID", "3");
        paramMap.put("AppID", "1000000253048925");
        paramMap.put("OperateID", "GET_GATEWAY_STATUS");
        paramMap.put("Secret", "cdbf9e272abce888");
        paramMap.put("MAC", "B4FBF91BE76A");
//        paramMap.put("channel_code", "300300");
        Resp resp = restTemplate.getForObject(url, Resp.class, paramMap);

        HttpEntity<HashMap> requestEntity = new HttpEntity<HashMap>(paramMap, headers);
//        Resp resp = restTemplate.getForObject(url, requestEntity.getClass(), paramMap);
        ResponseEntity<Resp> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Resp.class, paramMap);

//        String json = restTemplate.getForObject(url, String.class);
//        JSONObject json = restTemplate.getForEntity(url, JSONObject.class);
//        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
//        log.info(resp.toString());

        log.info(resp.toString());
        log.info(response.toString());
        log.info(response.getBody().toString());
        return response.getBody();
    }

}
