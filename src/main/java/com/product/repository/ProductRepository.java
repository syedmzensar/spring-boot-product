package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByProductName(String productName);

	List<Product> findByProductPrice(double productPrice);

	@Query(value = "select * from product where id=:id", nativeQuery = true)
	String checkForDiscount(@Param("id") int productId);
	
	@Query(value = "select * from product where price between 10000 and 100000", nativeQuery = true)
	List<Product> checkForRefund();
	
	
}
