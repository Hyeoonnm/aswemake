package com.aswemake.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String name;
    private Long memberId;
    private Long OrderListId;
}
