package com.flaya.service;

import com.flaya.domain.GirlInfo;
import com.flaya.repository.GirlRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GirlService girlService;

    @Test
    public void insertTwo() {
    }

    @Test
    public void getAge() {
    }


    @Test
    public void getByIdNew() {
//        Integer id = 1;
//        GirlInfo girlInfo = girlRepository.findById(id).orElse(null);
        girlService.getByIdNew(1);
        // 缓存存在
        // 从缓存中获取城市信息
        String key = "1";
        ValueOperations<String, GirlInfo> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            GirlInfo girlInfo = operations.get(key);
            log.info("GirlService.getById() : 从缓存中获取了实体 >> " + girlInfo.toString());
            log.info("【get】key:" + key + "-" + "value:" + girlInfo);
        }
    }

    @Test
    public void getById() {
    }
}