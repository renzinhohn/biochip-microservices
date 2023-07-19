package com.biochip.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BiochipConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiochipConfigServiceApplication.class, args);
	}

}
