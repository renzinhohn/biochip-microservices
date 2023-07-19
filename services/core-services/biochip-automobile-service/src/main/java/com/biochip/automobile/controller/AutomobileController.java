package com.biochip.automobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biochip.automobile.entity.Automobile;
import com.biochip.automobile.service.AutomobileService;

@RestController
@RequestMapping("/automobile")
public class AutomobileController {

	@Autowired
	private AutomobileService automobileService;
	
	@GetMapping
	public ResponseEntity<List<Automobile>> listCars() {
		List<Automobile> cars = automobileService.getAll();
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Automobile> obtainCar(@PathVariable("id") Long id) {
		Automobile car = automobileService.getCarById(id);
		if (car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}

	@PostMapping
	public ResponseEntity<Automobile> saveCar(@RequestBody Automobile car) {
		Automobile newCar = automobileService.save(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Automobile>> listCarsByCustomerId(@PathVariable("customerId") Long customerId) {
		List<Automobile> cars = automobileService.byCustomerId(customerId);
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
	
}
