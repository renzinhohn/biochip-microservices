package com.biochip.costumer.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorDetails {

	@NonNull
	private String errorMessage;
	
	@NonNull
	private String errorCode;
	
	@NonNull
	private LocalDateTime timestamp;
}
