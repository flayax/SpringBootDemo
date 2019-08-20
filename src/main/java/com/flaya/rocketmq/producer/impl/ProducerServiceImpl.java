package com.flaya.rocketmq.producer.impl;

import com.flaya.domain.OrderPaidEvent;
import com.flaya.rocketmq.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author:wangdong
 * @description:
 */
@Slf4j
@Service("rocketMQProducer")
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${demo.rocketmq.orderTopic}")
    private String orderPaidTopic;


    /**
     * 发送消息
     *
     * @return
     */
    @Override
    public void sendMessage() {
//        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
//        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
        // Send user-defined object

        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("T_001", new BigDecimal("88.00"));
        rocketMQTemplate.asyncSend(orderPaidTopic, orderPaidEvent, new SendCallback() {

            public void onSuccess(SendResult sendResult) {
                System.out.printf("async onSucess SendResult=%s %n", sendResult);
            }

            public void onException(Throwable e) {
                System.out.printf("async onException Throwable=%s %n", e);
            }

        });
        System.out.printf("syncSend to topic %s sendObj=%s %n", orderPaidTopic, orderPaidEvent);
    }
}
