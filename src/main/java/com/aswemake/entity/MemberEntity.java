package com.aswemake.entity;

import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public static MemberDTO toDTO(MemberEntity entity) {
        return MemberDTO.builder()
                .loginId(entity.getLoginId())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }
}
