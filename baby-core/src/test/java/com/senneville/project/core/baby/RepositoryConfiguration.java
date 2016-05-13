package com.senneville.project.core.baby;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.senneville.project.core.baby"})
@EnableJpaRepositories(basePackages = {"com.senneville.project.core.baby"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
