package com.aswemake.dto;

import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.enums.MemberEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String name;
    private MemberEnum memberEnum;

    public MemberEntity toEntity(MemberDTO dto) {
        return MemberEntity.builder().
                id(dto.id)
                .name(dto.name)
                .memberEnum(dto.getMemberEnum())
                .build();
    }
}
