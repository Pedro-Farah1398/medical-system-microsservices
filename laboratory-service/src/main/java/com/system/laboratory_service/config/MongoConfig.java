package com.system.laboratory_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.system.laboratory_service.repository.mongo"
)
public class MongoConfig {
}
