package com.aswemake.api;

import com.aswemake.dto.CouponDTO;
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
    public ResponseEntity<Integer> whole(@RequestBody CouponDTO dto) {
        int result = couponService.whole(dto.getMemberId(), dto.getCouponName());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/specific")
    public ResponseEntity<Integer> specific(@RequestBody CouponDTO dto) {
        int result = couponService.specific(dto.getMemberId(), dto.getCouponName(), dto.getProductId());
        return ResponseEntity.ok(result);
    }
}
