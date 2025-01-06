package com.realtrynna.spring_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/**
 * 서버 시작 시 AuditingEntityListener 활성화를 위함.
 */
@EnableJpaRepositories
public class SpringStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStartApplication.class, args);
	}
}