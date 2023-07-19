package com.biochip.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biochip.automobile.entity.Automobile;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Long>{

	List<Automobile> findByCustomerId(Long customerId);
}
