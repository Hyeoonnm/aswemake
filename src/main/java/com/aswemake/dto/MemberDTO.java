package com.aswemake.dto;

import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.enums.MemberRole;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String loginId;
    private String password;
    private MemberRole role;

    public static MemberEntity toEntity(MemberDTO dto) {
        return MemberEntity.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .role(MemberRole.ADMIN)
                .build();
    }

}
