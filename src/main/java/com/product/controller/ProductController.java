package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDto;
import com.product.service.ProductService;

@RestController

/** Have commented this out as get method is not working for war and jar
 * 	can be uncommented if use case requires
 * 	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
 * 	MediaType.APPLICATION_XML_VALUE }, consumes = {
 * 	MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
 **/
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") int productId) {
		return new ResponseEntity<ProductDto>(productService.getProduct(productId), HttpStatus.OK);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "productPrice") String propertyName,
			@RequestParam(required = false, defaultValue = "ASC") Direction direction) {
		return new ResponseEntity<List<ProductDto>>(
				productService.getAllProducts(pageNumber, pageSize, propertyName, direction), HttpStatus.OK);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<ProductDto> insertProduct(@RequestBody ProductDto productDto) {
		return new ResponseEntity<ProductDto>(productService.insertProduct(productDto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/products/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable("productId") int productId,
			@RequestBody ProductDto productDto) {
		productService.updateProduct(productId, productDto);
		return new ResponseEntity<String>("Product updated sucessfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<String>("Product deleted sucessfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/products/name/{productName}")
	public ResponseEntity<List<ProductDto>> findByProductName(@PathVariable String productName) {
		return new ResponseEntity<List<ProductDto>>(productService.findByProductName(productName), HttpStatus.OK);
	}

	@GetMapping("/products/price/{productPrice}")
	public ResponseEntity<List<ProductDto>> findByProductPrice(@PathVariable double productPrice) {
		return new ResponseEntity<List<ProductDto>>(productService.findByProductPrice(productPrice), HttpStatus.OK);
	}

	@GetMapping("/products/discount/{productId}")
	public ResponseEntity<String> checkForDiscount(@PathVariable int productId) {
		return new ResponseEntity<String>(productService.checkForDiscount(productId), HttpStatus.OK);
	}

	@GetMapping("/products/refund")
	public ResponseEntity<List<ProductDto>> checkForRefund() {
		return new ResponseEntity<List<ProductDto>>(productService.checkForRefund(), HttpStatus.OK);
	}

}
