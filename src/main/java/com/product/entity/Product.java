package com.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int productId;

	@Column(name = "name")
	private String productName;

	@Column(name = "price")
	private double productPrice;

	@Transient
	private String couponCode;

	/*
	 * public Product() { super(); }
	 * 
	 * public Product(int productId, String productName, double productPrice) {
	 * super(); this.productId = productId; this.productName = productName;
	 * this.productPrice = productPrice; }
	 * 
	 * public int getProductId() { return productId; }
	 * 
	 * public void setProductId(int productId) { this.productId = productId; }
	 * 
	 * public String getProductName() { return productName; }
	 * 
	 * public void setProductName(String productName) { this.productName =
	 * productName; }
	 * 
	 * public double getProductPrice() { return productPrice; }
	 * 
	 * public void setProductPrice(double productPrice) { this.productPrice =
	 * productPrice; }
	 * 
	 * @Override public String toString() { return "Product [productId=" + productId
	 * + ", productName=" + productName + ", productPrice=" + productPrice + "]"; }
	 */
}
