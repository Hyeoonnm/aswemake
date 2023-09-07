package com.aswemake.api;

import com.aswemake.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon/api")
public class CouponApi {

    private final CouponService couponService;

    @PostMapping("/whole")
    public ResponseEntity<Integer> whole(@RequestBody Long memberId, String couponName) {
        int result = couponService.whole(memberId,couponName);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/specific")
    public ResponseEntity<Integer> specific(@RequestBody Long memberId, String couponName, Long productId) {
        int result = couponService.specific(memberId,couponName,productId);
        return ResponseEntity.ok(result);
    }
}
