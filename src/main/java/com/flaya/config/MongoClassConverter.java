package com.flaya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * https://stackoverflow.com/questions/23517977/spring-boot-mongodb-how-to-remove-the-class-column
 * https://github.com/techial1042/Blog/issues/57
 */
@Configuration
public class MongoClassConverter {
    private final MongoDbFactory mongoDbFactory;
    private final MongoMappingContext mongoMappingContext;

    public MongoClassConverter(MongoDbFactory mongoDbFactory, MongoMappingContext mongoMappingContext) {
        this.mongoDbFactory = mongoDbFactory;
        this.mongoMappingContext = mongoMappingContext;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

}