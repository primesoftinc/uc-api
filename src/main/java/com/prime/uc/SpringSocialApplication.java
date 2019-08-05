package com.prime.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.prime.uc.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@ComponentScan(basePackages = "com.prime")
public class SpringSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialApplication.class, args);
	}
}
