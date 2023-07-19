package com.biochip.motorcycle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biochip.motorcycle.entity.Motorcycle;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long>{

	List<Motorcycle> findByCustomerId(Long customerId);
}
