package com.senneville.project.core.baby;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
//@PropertySource("classpath:baby-core-test-application.properties")
@EntityScan(basePackages = "com.senneville.project.core.baby")
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.senneville.project.core.baby")
@EnableTransactionManagement(proxyTargetClass = true)
public interface TestJpaConf {

}
