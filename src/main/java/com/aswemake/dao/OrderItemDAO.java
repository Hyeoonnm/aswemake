package com.aswemake.dao;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItemEntity, Long> {

    void deleteByMemberIdAndProductId(Long memberId, Long productId);

    List<OrderItemEntity> findAllByMemberId(Long memberId);
}
