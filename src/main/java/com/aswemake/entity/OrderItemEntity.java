package com.aswemake.entity;

import com.aswemake.dto.OrderItemDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private int count;

    public int getTotalPrice() {
        return product.getPrice() * getCount();
    }

    public static OrderItemDTO toDTO(OrderItemEntity entity) {
        return OrderItemDTO.builder()
                .memberId(entity.getId())
                .productId(entity.getId())
                .count(entity.getCount())
                .totalPrice(entity.getTotalPrice())
                .build();
    }
}
