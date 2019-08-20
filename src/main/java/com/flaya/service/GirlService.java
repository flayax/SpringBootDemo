package com.flaya.service;

import com.flaya.domain.GirlInfo;
import com.flaya.enums.ResultEnum;
import com.flaya.exception.GirlException;
import com.flaya.repository.GirlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "girl")
public class GirlService  {

    private static Logger logger = LoggerFactory.getLogger(GirlService.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public void insertTwo() {
        GirlInfo girlA = new GirlInfo();
        girlA.setAge(18);
        girlA.setCupSize("E");
        girlRepository.save(girlA);

        GirlInfo girlB = new GirlInfo();
        girlB.setAge(19);
        girlB.setCupSize("FFFFFF");
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        GirlInfo girlInfo = girlRepository.findById(id).orElse(null);
        if (girlInfo == null) {
            throw new GirlException(ResultEnum.UNKNOWN_DATA);
        }
        int age = girlInfo.getAge();
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age >= 10 && age < 14) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    // 添加缓存配置
    @Cacheable(key = "'getById'")
    public GirlInfo getByIdNew(Integer id) {
        GirlInfo girlInfo = girlRepository.findById(id).orElse(null);
//        // 缓存存在
//        // 从缓存中获取城市信息
//        String key = "getById";
//        ValueOperations<String, GirlInfo> operations = redisTemplate.opsForValue();
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            GirlInfo girlInfo1 = operations.get(key);
//            logger.info("GirlService.getById() : 从缓存中获取了实体 >> " + girlInfo1.toString());
//            logger.info("【get】key:" + key + "-" + "value:" + girlInfo1);
//            return girlInfo1;
//        }
        return girlInfo;
    }

    // 添加缓存配置
    @Cacheable(key = "'getByIddddddddd'")
    public GirlInfo getByIdNew1(Integer id) {
        GirlInfo girlInfo = girlRepository.findById(id).orElse(null);
//        // 缓存存在
//        // 从缓存中获取城市信息
//        String key = "getById";
//        ValueOperations<String, GirlInfo> operations = redisTemplate.opsForValue();
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            GirlInfo girlInfo1 = operations.get(key);
//            logger.info("GirlService.getById() : 从缓存中获取了实体 >> " + girlInfo1.toString());
//            logger.info("【get】key:" + key + "-" + "value:" + girlInfo1);
//            return girlInfo1;
//        }
        return girlInfo;
    }

//    @Cacheable(value = "girl")
    public GirlInfo getById(Integer id) {
        // 从缓存中获取城市信息
        String key = "girl_" + id;
        ValueOperations<String, GirlInfo> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            GirlInfo girlInfo = operations.get(key);
            logger.info("GirlService.getById() : 从缓存中获取了实体 >> " + girlInfo.toString());
            logger.info("【get】key:" + key + "-" + "value:" + girlInfo);
            return girlInfo;
        }
        // 从DB中获取实体信息
        GirlInfo girlInfo = girlRepository.findById(id).orElse(null);
        // 插入缓存
        operations.set(key, girlInfo, 10, TimeUnit.MINUTES);
        logger.info("CityServiceImpl.findCityById() : 实体插入缓存 >> " + girlInfo.toString());
        logger.info("【set】key:" + key + "-" + "value:" + girlInfo);
        return girlInfo;
    }
}
