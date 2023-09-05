package com.apis;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.apis.repository")
@EnableDiscoveryClient
public class RestApiApplication {

	final static Logger logger = LoggerFactory.getLogger(RestApiApplication.class);
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	public static void main(String[] args) {
		logger.info("Starting rest-api application.");
		SpringApplication.run(RestApiApplication.class, args);
	}

}
