package com.biochip.costumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biochip.costumer.entity.Customer;
import com.biochip.costumer.feignclient.AutomobileFeignClient;
import com.biochip.costumer.feignclient.MotorcycleFeignClient;
import com.biochip.costumer.models.Automobile;
import com.biochip.costumer.models.Motorcycle;
import com.biochip.costumer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private AutomobileFeignClient automobileFeignClient;
	
	@Autowired
	private MotorcycleFeignClient motorcycleFeignClient;
	
	public List<Customer> getAll(){
		return repository.findAll();
	}
	
	public Customer getCustomerById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Customer save(Customer customer) {
		Customer newCustomer = repository.save(customer);
		return newCustomer;
	}
	
	public List<Automobile> getAutomobiles(Long customerId){
		//List<Automobile> list = restTemplate.getForObject("http://localhost:8082/automobile/customer/{customerId}", List.class, customerId);
		List<Automobile> list = restTemplate.getForObject("http://automobile-service/automobile/customer/{customerId}", List.class, customerId);
		return list;
	}
	
	public List<Motorcycle> getMotorcycles(Long customerId){
		//List<Motorcycle> list = restTemplate.getForObject("http://localhost:8083/motorcycle/customer/{customerId}", List.class, customerId);
		List<Motorcycle> list = restTemplate.getForObject("http://motorcycle-service/motorcycle/customer/{customerId}", List.class, customerId);
		return list;
	}
	
	public Automobile saveAutomobile(Long customerId, Automobile automobile) {
		automobile.setCustomerId(customerId);
		Automobile newAutomobile = automobileFeignClient.save(automobile);
		return newAutomobile;
	}
	
	public Motorcycle saveMotorcycle(Long customerId, Motorcycle motorcycle) {
		motorcycle.setCustomerId(customerId);
		Motorcycle newMotorcycle = motorcycleFeignClient.save(motorcycle);
		return newMotorcycle;
	}
	
	public Map<String, Object> getAllVehicles(Long customerId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Customer customer = repository.findById(customerId).orElse(null);		
		if(customer == null) {
			result.put("Message", "The user doesn't exist");
			return result;
		}		
		result.put("Customer", customer);
		
		List<Automobile> automobiles = automobileFeignClient.getAutomobiles(customerId);
		if(automobiles.isEmpty()) {
			result.put("Automobiles", "The customer don't have automobiles");
		}
		result.put("Automobiles", automobiles);
		
		List<Motorcycle> motorcycles = motorcycleFeignClient.getMotorcycles(customerId);
		if(automobiles.isEmpty()) {
			result.put("Motorcycles", "The customer don't have motorcycles");
		}
		result.put("Motorcycles", motorcycles);
		
		return result;
	}
}
