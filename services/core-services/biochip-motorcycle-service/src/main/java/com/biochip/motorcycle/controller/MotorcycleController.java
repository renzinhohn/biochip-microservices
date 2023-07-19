package com.biochip.motorcycle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biochip.motorcycle.entity.Motorcycle;
import com.biochip.motorcycle.service.MotorcycleService;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {

	@Autowired
	private MotorcycleService motorcycleService;
	
	@GetMapping
	public ResponseEntity<List<Motorcycle>> listMotorcycles() {
		List<Motorcycle> motorcycles = motorcycleService.getAll();
		if (motorcycles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motorcycles);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Motorcycle> obtainMotorcycle(@PathVariable("id") Long id) {
		Motorcycle motorcycle = motorcycleService.getMotorcycleById(id);
		if (motorcycle == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(motorcycle);
	}

	@PostMapping
	public ResponseEntity<Motorcycle> saveMotorcycle(@RequestBody Motorcycle car) {
		Motorcycle newMotorcycle = motorcycleService.save(car);
		return ResponseEntity.ok(newMotorcycle);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Motorcycle>> listMotorcyclesByUserId(@PathVariable("customerId") Long customerId) {
		List<Motorcycle> motorcycles = motorcycleService.byCustomerId(customerId);
		if (motorcycles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motorcycles);
	}
	
}
