package com.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

	private int productId;

	private String productName;

	private double productPrice;
	
	private String couponCode;

	/*
	 * public ProductDto() { super(); }
	 * 
	 * public ProductDto(int productId, String productName, double productPrice) {
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
