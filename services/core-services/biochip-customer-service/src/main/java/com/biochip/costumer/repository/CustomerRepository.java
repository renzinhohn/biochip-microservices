package com.biochip.costumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biochip.costumer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
