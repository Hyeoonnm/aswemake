package com.aswemake.service;

import com.aswemake.dto.OrderItemDTO;

import java.util.List;

public interface OrderListService {
    OrderItemDTO save(OrderItemDTO dto);

    List<OrderItemDTO> findAll();

    void delete(Long memberId, Long productId);
}
