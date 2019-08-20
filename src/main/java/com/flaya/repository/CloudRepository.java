package com.flaya.repository;

import com.flaya.domain.param.CloudParam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudRepository extends MongoRepository<CloudParam, String> {
}
