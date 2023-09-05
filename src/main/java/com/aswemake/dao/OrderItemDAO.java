package com.aswemake.dao;

import com.aswemake.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItemEntity, Long> {

    void deleteByMemberIdAndProductId(Long memberId, Long productId);
}
