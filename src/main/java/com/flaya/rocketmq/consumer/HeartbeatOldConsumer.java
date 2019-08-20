package com.flaya.rocketmq.consumer;

import com.flaya.domain.param.LogReq;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "${demo.rocketmq.oldHeartbeatTopic}", consumerGroup = "${demo.rocketmq.oldHeartbeatGroup}")
public class HeartbeatOldConsumer implements RocketMQListener<LogReq> {

    @Override
    public void onMessage(LogReq message) {
        System.out.printf("------- HeartbeatOldConsumer received: %s \n", message);
    }
}
