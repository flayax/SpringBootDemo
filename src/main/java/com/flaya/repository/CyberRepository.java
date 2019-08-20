package com.flaya.repository;

import com.flaya.domain.CyberInform;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CyberRepository extends MongoRepository<CyberInform, Integer> {

    CyberInform findByStreamSessionId(String streamSessionId);

    List<CyberInform> findAllByStreamSessionId(String streamSessionId);

    List<CyberInform> findAllByUserInfosName(String name);

    List<CyberInform> findAllByUserInfosPhone(String phone);

    // 此方法相当于findByUserInfosName
    @Query("{ 'userInfos.name': { $regex: ?0, $options: 'i' } }")
    List<CyberInform> findByName(String name);
}
