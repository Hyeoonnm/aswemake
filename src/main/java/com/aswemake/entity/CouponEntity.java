package com.aswemake.entity;

import com.aswemake.entity.enums.CouponRange;
import com.aswemake.entity.enums.CouponType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "coupon")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CouponType couponType;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CouponRange couponRange;
}
