package com.biochip.costumer.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biochip.costumer.models.Motorcycle;

@FeignClient(name = "motorcycle-service")
public interface MotorcycleFeignClient {

	@PostMapping("/motorcycle")
	public Motorcycle save(@RequestBody Motorcycle motorcycle);
	
	@GetMapping("/motorcycle/customer/{customerId}")
	public List<Motorcycle> getMotorcycles(@PathVariable("customerId") Long customerId);
}
