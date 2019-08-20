package com.flaya.controller;

import com.flaya.rocketmq.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/spring_boot/rocketmq")
public class RocketMQController {

    @Resource(name = "rocketMQProducer")
    private ProducerService producerService;

    @RequestMapping("/send_msg")
    public Object sendMsg(){
        producerService.sendMessage();
        return "success";
    }
}
