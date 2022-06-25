package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.exception.ProductAlreadyExistsException;
import com.product.exception.ProductNotFoundException;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ProductDto getProduct(int productId) {
		Product product = productRepository.findById(productId).get();

		if (product.getProductId() != productId)
			throw new ProductNotFoundException("Product with this id is not there");
		else
			return modelMapper.map(product, ProductDto.class);

	}

	public List<ProductDto> getAllProducts(int pageNumber, int pageSize, String propertyName, Direction direction) {
		List<ProductDto> listOfProductsDto = new ArrayList<ProductDto>();

		Page<Product> findAllProducts = productRepository
				.findAll(PageRequest.of(pageNumber, pageSize, direction, propertyName));

		List<Product> products = findAllProducts.getContent();

		for (Product product : products) {
			listOfProductsDto.add(modelMapper.map(product, ProductDto.class));
		}

		return listOfProductsDto;
	}

	public ProductDto insertProduct(ProductDto productDto) {

		Product product = modelMapper.map(productDto, Product.class);

		boolean checkId = productRepository.existsById(product.getProductId());

		if (checkId) {
			throw new ProductAlreadyExistsException("This product already exists");
		}

		Product productDtoInsert = productRepository.save(product);

		return modelMapper.map(productDtoInsert, ProductDto.class);

//		return productRepository.save(product);
	}

	public void updateProduct(int productId, ProductDto productDto) {

		productRepository.save(modelMapper.map(productDto, Product.class));

	}

	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);

	}

	public List<ProductDto> findByProductName(String productName) {
		List<Product> products = productRepository.findByProductName(productName);
		List<ProductDto> listOfProductsDto = new ArrayList<ProductDto>();

		for (Product product : products) {
			listOfProductsDto.add(modelMapper.map(product, ProductDto.class));
		}

		return listOfProductsDto;
	}

	public List<ProductDto> findByProductPrice(double productPrice) {
		List<Product> products = productRepository.findByProductPrice(productPrice);
		List<ProductDto> listOfProductsDto = new ArrayList<ProductDto>();

		for (Product product : products) {
			listOfProductsDto.add(modelMapper.map(product, ProductDto.class));
		}

		return listOfProductsDto;
	}

	public String checkForDiscount(int productId) {
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			if (product.getProductId() == productId) {
				if (product.getProductPrice() > 40000) {
					return "You can avail a discount of 20%";
				}
				return "Use AMAZE20 to get 10% on your order";
			}
		}
		return "Id of this product does not exist";
	}

	public List<ProductDto> checkForRefund() {
		List<Product> products = productRepository.checkForRefund();

		List<ProductDto> listOfProductsDto = new ArrayList<ProductDto>();

		for (Product product : products) {
			listOfProductsDto.add(modelMapper.map(product, ProductDto.class));
		}

		return listOfProductsDto;
	}

}
