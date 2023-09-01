package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    @Override
    public MemberDTO signup(MemberDTO memberDTO) {
        MemberDTO dto = new MemberDTO();

        MemberEntity memberEntity = dto.toEntity(memberDTO);

        MemberEntity save = memberDAO.save(memberEntity);

        return memberEntity.toDto(save);
    }
}
