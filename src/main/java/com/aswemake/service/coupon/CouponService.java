package com.aswemake.service.coupon;

public interface CouponService {

    int whole(Long memberId, String couponName);

    int specific(Long memberId, String couponName, Long productId);
}
