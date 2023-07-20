package com.biochip.costumer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biochip.costumer.entity.Customer;
import com.biochip.costumer.models.Automobile;
import com.biochip.costumer.models.Motorcycle;
import com.biochip.costumer.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> listCustomers() {
		List<Customer> customers = customerService.getAll();
		if (customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomerById(id);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}

	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer user) {
		Customer newCustomer = customerService.save(user);
		System.out.println(newCustomer.toString());
		return ResponseEntity.ok(newCustomer);
	}
	
	@CircuitBreaker(name = "automobilesCB", fallbackMethod = "fallBackGetAutomobiles")
	@GetMapping("/automobiles/{customerId}")
	public ResponseEntity<List<Automobile>> getAutomobiles(@PathVariable("customerId") Long customerId){
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		List<Automobile> cars = customerService.getAutomobiles(customerId);
		return ResponseEntity.ok(cars);
	}

	@CircuitBreaker(name = "motorcyclesCB", fallbackMethod = "fallBackGetMotorcycles")
	@GetMapping("/motorcycles/{customerId}")
	public ResponseEntity<List<Motorcycle>> getMotorcycles(@PathVariable("customerId") Long customerId){
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		List<Motorcycle> motorcycles = customerService.getMotorcycles(customerId);
		return ResponseEntity.ok(motorcycles);
	}
	
	@CircuitBreaker(name = "automobilesCB", fallbackMethod = "fallBackSaveAutomobile")
	@PostMapping("/automobile/{customerId}")
	public ResponseEntity<Automobile> saveAutomobile(@PathVariable("customerId") Long customerId, @RequestBody Automobile automobile){
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		Automobile newAutomobile = customerService.saveAutomobile(customerId, automobile);
		return ResponseEntity.ok(newAutomobile);
	}
	
	@CircuitBreaker(name = "motorcyclesCB", fallbackMethod = "fallBackSaveMotorcycle")
	@PostMapping("/motorcycle/{customerId}")
	public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable("customerId") Long customerId, @RequestBody Motorcycle motorcycle){
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		Motorcycle newMotorcycle = customerService.saveMotorcycle(customerId, motorcycle);
		return ResponseEntity.ok(newMotorcycle);
	}
	
	@CircuitBreaker(name = "vehiclesCB", fallbackMethod = "fallBackGetAllVehicles")
	@GetMapping("/vehicles/{customerId}")
	public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("customerId") Long customerId){
		Map<String, Object> result = customerService.getAllVehicles(customerId);
		return ResponseEntity.ok(result);
	}
	
	private ResponseEntity<List<Automobile>> fallBackGetAutomobiles(@PathVariable("customerId") Long customerId, RuntimeException e){
		return new ResponseEntity("The customer: "+customerId+" have their automobiles in the workshop", HttpStatus.OK);
	}
	
	private ResponseEntity<List<Automobile>> fallBackSaveAutomobile(@PathVariable("customerId") Long customerId, @RequestBody Automobile automobile, RuntimeException e){
		return new ResponseEntity("The customer: "+customerId+" don't have enough money to pay the automobile", HttpStatus.OK);
	}
	
	private ResponseEntity<List<Motorcycle>> fallBackGetMotorcycles(@PathVariable("customerId") Long customerId, RuntimeException e){
		return new ResponseEntity("The customer: "+customerId+" have their motorcycles in the workshop", HttpStatus.OK);
	}
	
	private ResponseEntity<List<Motorcycle>> fallBackSaveMotorcycle(@PathVariable("customerId") Long customerId, @RequestBody Motorcycle motorcycle, RuntimeException e){
		return new ResponseEntity("The customer: "+customerId+" don't have enough money to pay the motorcycle", HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> fallBackGetAllVehicles(@PathVariable("customerId") Long customerId, RuntimeException e){
		return new ResponseEntity("The customer: "+customerId+" have all their vehicles in the workshop", HttpStatus.OK);
	}
}
