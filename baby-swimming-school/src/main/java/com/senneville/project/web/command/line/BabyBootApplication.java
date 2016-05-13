package com.senneville.project.web.command.line;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.senneville.project.core.baby.ClientCrudRepository;
import com.senneville.project.core.baby.CourseCrudRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.senneville.project.core.baby")
@EntityScan(basePackages = "com.senneville.project.core.baby")
public class BabyBootApplication implements CommandLineRunner {

	@Autowired
	private ClientCrudRepository clientCrudRepository;

	@Autowired
    private CourseCrudRepository courseCrudRepository;

	@Autowired
	private BabyDataGenerator babyDataGenerator;
	
    public static void main(String[] args) {
        SpringApplication.run(BabyBootApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		this.babyDataGenerator.create();
		
        this.clientCrudRepository.findAll().spliterator().forEachRemaining(b -> System.out.println(b));
		this.courseCrudRepository.findAll().spliterator().forEachRemaining(b -> System.out.println(b));
		
		this.clientCrudRepository.findByLastName("Massacré").spliterator().forEachRemaining(b -> System.out.println(b));
	}
}
