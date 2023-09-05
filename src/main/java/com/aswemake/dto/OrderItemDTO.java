package com.aswemake.dto;

import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.OrderItemEntity;
import com.aswemake.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long productId;
    private int count;
    private Long memberId;

    private int totalPrice;

    public static OrderItemEntity toEntity(OrderItemDTO dto, MemberEntity member, ProductEntity product) {
        return OrderItemEntity.builder()
                .product(product)
                .member(member)
                .count(dto.getCount())
                .build();
    }

}
