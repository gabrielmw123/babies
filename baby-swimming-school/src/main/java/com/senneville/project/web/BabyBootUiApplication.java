package com.senneville.project.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.senneville.project.web.ui.MainUi;
import com.vaadin.ui.UI;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.senneville.project.core.baby")
@EntityScan(basePackages = "com.senneville.project.core.baby")
public class BabyBootUiApplication extends SpringBootServletInitializer {

	@Bean
	@Scope("prototype")
	public UI ui() {
		return new MainUi();
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new ru.xpoft.vaadin.SpringVaadinServlet(),
				"/*",
				"/VAADIN/*");
		return servletRegistrationBean;
	}
	
	public static void main(String[] args) {
        SpringApplication.run(BabyBootUiApplication.class, args);
    }
}
