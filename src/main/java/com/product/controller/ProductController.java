package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDto;
import com.product.entity.CouponDto;
import com.product.model.AuthenticationRequest;
import com.product.model.AuthenticationResponse;
import com.product.restclient.CouponRestClient;
import com.product.security.MyUserDetailsService;
import com.product.service.ProductService;
import com.product.util.JwtUtil;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CouponRestClient restClient;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

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

		CouponDto couponDto = restClient.getCoupon(productDto.getCouponCode());

		productDto.setProductPrice(productDto.getProductPrice() - couponDto.getDiscount());

//		ModelMapper  modelMapper = new ModelMapper();

//		Product product = modelMapper.map(productDto, Product.class);
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
