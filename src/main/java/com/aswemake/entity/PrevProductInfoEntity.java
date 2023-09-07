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

    public static PrevProductInfoEntity toEntity(ProductDTO dto, PrevProductInfoEntity prevInfo, int price, ProductEntity entity) {

        // 첫 수정 상품
        if (prevInfo == null) {
            return PrevProductInfoEntity.builder()
                    .price(price)
                    .startDate(dto.getCreateDate())
                    .endDate(dto.getModifiedDate())
                    .product(entity)
                    .build();
        } else {
            // 한 번 이후의 수정 상품
            return PrevProductInfoEntity.builder()
                    .price(price)
                    .startDate(prevInfo.getEndDate())
                    .endDate(dto.getModifiedDate())
                    .product(entity)
                    .build();
        }
    }
}
