package com.biochip.motorcycle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "motorcycle")
public class Motorcycle extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "customerId")
	private Long customerId;
}
