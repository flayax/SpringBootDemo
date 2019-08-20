package com.flaya.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

//@Configuration
public class MongoConfig_ extends AbstractMongoConfiguration {

//
    @Value("${spring.data.mongodb.host}")
    private String MONGO_IP;

    @Value("${spring.data.mongodb.port}")
    private int MONGO_PORT;

    @Value("${spring.data.mongodb.database}")
    private String DB_NAME;

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(MONGO_IP, MONGO_PORT);
    }


    // 为了去除“_class”字段
    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter mmc = super.mappingMongoConverter();
        mmc.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mmc;
    }
}
