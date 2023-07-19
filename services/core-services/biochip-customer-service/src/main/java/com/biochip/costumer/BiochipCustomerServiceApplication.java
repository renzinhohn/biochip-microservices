package com.biochip.costumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BiochipCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiochipCustomerServiceApplication.class, args);
	}

}
