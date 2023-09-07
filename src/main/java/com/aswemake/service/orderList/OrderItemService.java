package com.aswemake.service.orderList;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.repOrderItemDTO;

public interface OrderItemService {
    OrderItemDTO save(OrderItemDTO dto);

    void delete(Long memberId, Long productId);

    repOrderItemDTO findAllByMemberId(Long memberId);

    int total(Long memberId);
}
