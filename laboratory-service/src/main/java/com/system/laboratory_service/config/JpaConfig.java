package com.system.laboratory_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.system.laboratory_service.repository.postgres"
)
public class JpaConfig {
}
