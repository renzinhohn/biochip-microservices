package com.biochip.motorcycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BiochipMotorcycleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiochipMotorcycleServiceApplication.class, args);
	}

}
