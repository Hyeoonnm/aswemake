package com.aswemake.dto;

import com.aswemake.entity.PrevProductInfoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrevProductInfoDTO {
    private Long id;
    private int price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static PrevProductInfoDTO toDTO(PrevProductInfoEntity entity) {
        return PrevProductInfoDTO.builder()
                .price(entity.getPrice())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
