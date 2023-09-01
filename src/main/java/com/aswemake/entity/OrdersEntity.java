package com.aswemake.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.invoke.CallSite;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderListEntity> ordersList;

    private int deliveryPrice;
}
