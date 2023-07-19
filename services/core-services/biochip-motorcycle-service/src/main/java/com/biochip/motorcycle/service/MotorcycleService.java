package com.biochip.motorcycle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biochip.motorcycle.entity.Motorcycle;
import com.biochip.motorcycle.repository.MotorcycleRepository;

@Service
public class MotorcycleService {

	@Autowired
	private MotorcycleRepository repository;
	
	public List<Motorcycle> getAll(){
		return repository.findAll();
	}
	
	public Motorcycle getMotorcycleById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Motorcycle save(Motorcycle car) {
		Motorcycle newMotorcycle = repository.save(car);
		return newMotorcycle;
	}
	
	public List<Motorcycle> byCustomerId(Long customerId){
		return repository.findByCustomerId(customerId);
	}
}
