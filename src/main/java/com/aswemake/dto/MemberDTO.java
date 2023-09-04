package com.aswemake.dto;

import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.enums.MemberEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String username;
    private String password;
    private MemberEnum role;

    public static MemberEntity toEntity(MemberDTO dto) {
        return MemberEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(MemberEnum.USER)
                .build();
    }

}
