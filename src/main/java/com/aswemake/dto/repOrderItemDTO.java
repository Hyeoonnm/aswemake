package com.aswemake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class repOrderItemDTO {
    private List<ProductDTO> product;
    private List<Integer> count;

    private int delivery;
}
