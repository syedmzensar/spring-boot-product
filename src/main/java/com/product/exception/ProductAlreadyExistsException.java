package com.product.exception;

public class ProductAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public ProductAlreadyExistsException() {
		super();
	}

	public ProductAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

}
