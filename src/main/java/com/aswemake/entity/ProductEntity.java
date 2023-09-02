package com.aswemake.entity;

import com.aswemake.dto.ProductDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int price;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public static ProductDTO toDTO(ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .createDate(entity.getCreateDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }
}
