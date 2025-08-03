package com.assslash.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AssslashApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssslashApiApplication.class, args);
	}

}
