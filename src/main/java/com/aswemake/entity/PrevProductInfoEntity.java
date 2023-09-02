package com.aswemake.entity;

import com.aswemake.dto.ProductDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "prev_product_info")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PrevProductInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prev_product_id")
    private Long id;
    private int price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public static PrevProductInfoEntity toEntity(ProductDTO dto) {
        ProductEntity entity = ProductDTO.toEntity(dto);
        return PrevProductInfoEntity.builder()
                .price(dto.getPrice())
                .startDate(dto.getCreateDate())
                .endDate(dto.getModifiedDate())
                .product(entity)
                .build();
    }
}
