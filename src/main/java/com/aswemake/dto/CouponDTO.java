package com.aswemake.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
    private Long memberId;
    private Long productId;
    private String couponName;

}
