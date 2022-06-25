package com.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CouponDto {

	private int couponId;

	private String couponCode;
	
	private Double discount;

	private String couponExpiry;

	/*
	 * public CouponDto() { super(); }
	 * 
	 * public CouponDto(int couponId, int couponCode, String couponExpiry) {
	 * super(); this.couponId = couponId; this.couponCode = couponCode;
	 * this.couponExpiry = couponExpiry; }
	 * 
	 * public int getCouponId() { return couponId; }
	 * 
	 * public void setCouponId(int couponId) { this.couponId = couponId; }
	 * 
	 * public int getCouponCode() { return couponCode; }
	 * 
	 * public void setCouponCode(int couponCode) { this.couponCode = couponCode; }
	 * 
	 * public String getCouponExpiry() { return couponExpiry; }
	 * 
	 * public void setCouponExpiry(String couponExpiry) { this.couponExpiry =
	 * couponExpiry; }
	 * 
	 * @Override public String toString() { return "Coupon [couponId=" + couponId +
	 * ", couponCode=" + couponCode + ", couponExpiry=" + couponExpiry + "]"; }
	 */
}