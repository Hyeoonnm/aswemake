package com.aswemake.entity;

import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.enums.MemberEnum;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 이름
    @Column(name = "member_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberEnum memberEnum;

    public MemberDTO toDto(MemberEntity memberEntity) {
        return MemberDTO.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .memberEnum(memberEntity.getMemberEnum())
                .build();
    }
}
