package com.aswemake.dto;

import com.aswemake.entity.ProductEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private int price;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    public static ProductEntity toEntity(ProductDTO dto) {
        return ProductEntity.builder()
                .name(dto.getName())
                .price(dto.price)
                .createDate(dto.getCreateDate())
                .modifiedDate(dto.getModifiedDate())
                .build();
    }
}
