package com.aswemake.dao;

import com.aswemake.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDAO extends JpaRepository<CouponEntity, Long> {
    CouponEntity findByName(String couponName);
}
