package com.aswemake.dto;

import com.aswemake.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String name;
    private String password;
    private String role;

    public static MemberEntity toEntity(MemberDTO dto) {
        return MemberEntity.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }
}
