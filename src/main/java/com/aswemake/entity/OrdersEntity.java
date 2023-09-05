package com.aswemake.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany
    @JoinColumn(name = "order_item_id")
    private List<OrderItemEntity> ordersList;

    private int deliveryPrice;

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItemEntity orderList : ordersList) {
            totalPrice += orderList.getTotalPrice();
        }
        return totalPrice;
    }
}
