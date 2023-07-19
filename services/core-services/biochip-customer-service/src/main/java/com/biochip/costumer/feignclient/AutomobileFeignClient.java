package com.biochip.costumer.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biochip.costumer.models.Automobile;

@FeignClient(name = "automobile-service",url = "http://localhost:8082")
public interface AutomobileFeignClient {

	@PostMapping("/automobile")
	public Automobile save(@RequestBody Automobile automobile);
	
	@GetMapping("/automobile/customer/{customerId}")
	public List<Automobile> getAutomobiles(@PathVariable("customerId") Long customerId);
}
