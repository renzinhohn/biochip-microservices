package com.biochip.automobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biochip.automobile.entity.Automobile;
import com.biochip.automobile.repository.AutomobileRepository;

@Service
public class AutomobileService {

	@Autowired
	private AutomobileRepository repository;
	
	public List<Automobile> getAll(){
		return repository.findAll();
	}
	
	public Automobile getCarById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Automobile save(Automobile car) {
		Automobile newCar = repository.save(car);
		return newCar;
	}
	
	public List<Automobile> byCustomerId(Long customerId){
		return repository.findByCustomerId(customerId);
	}
}
