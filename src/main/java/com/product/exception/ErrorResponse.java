package com.product.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	
	private int statusCode;
	private String message;

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

}
