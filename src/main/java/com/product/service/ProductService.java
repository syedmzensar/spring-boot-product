package com.product.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.product.dto.ProductDto;

public interface ProductService {

	public ProductDto getProduct(int productId);

	public List<ProductDto> getAllProducts(int pageNumber, int pageSize, String propertyName, Direction direction);

	public ProductDto insertProduct(ProductDto productDto);

	public void updateProduct(int productId, ProductDto productDto);

	public void deleteProduct(int productId);
	
	List<ProductDto> findByProductName(String productName);
	
	List<ProductDto> findByProductPrice(double productPrice);
	
	String checkForDiscount(int productId);
	
	List<ProductDto> checkForRefund();

}
