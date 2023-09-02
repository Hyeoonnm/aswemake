package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberDAO memberDAO;
    private final PasswordEncoder encoder;
    @Override
    public MemberDTO findByName(String name) {
        MemberEntity byName = memberDAO.findByName(name);
        MemberDTO dto = MemberEntity.toDTO(byName);
        return dto;
    }

    @Override
    public MemberDTO signup(MemberDTO member) {
        member.setRole("ROLE_" + member.getRole());
        member.setPassword(encoder.encode(member.getPassword()));
        MemberEntity entity = MemberDTO.toEntity(member);
        MemberEntity save = memberDAO.save(entity);
        return MemberEntity.toDTO(save);
    }
}
