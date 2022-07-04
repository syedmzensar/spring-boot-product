package com.product.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.entity.CouponDto;

@FeignClient("COUPON-SERVICE")
public interface CouponRestClient {

	@GetMapping("/coupons/{couponCode}")
	CouponDto getCoupon(@PathVariable("couponCode") String couponCode);
}
