package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder encoder;

    @Override
    public MemberDTO signup(MemberDTO member) throws Exception {
        if (memberDAO.findByLoginId(member.getLoginId()).isPresent()) {
            return null;
        }

        member.setPassword(encoder.encode(member.getPassword()));
        MemberEntity memberEntity = MemberDTO.toEntity(member);

        MemberEntity saveMember = memberDAO.save(memberEntity);

        return MemberEntity.toDTO(saveMember);
    }
    
    @Transactional(readOnly = true)
    public MemberEntity login(MemberDTO dto) {
        Optional<MemberEntity> optionalUser = memberDAO.findByLoginId(dto.getLoginId());
        if(optionalUser.isEmpty()) {
            return null;
        }

        MemberEntity member = optionalUser.get();
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            return null;
        }
        return member;
    }

    @Transactional(readOnly = true)
    public MemberEntity getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<MemberEntity> optionalUser = memberDAO.findByLoginId(loginId);
        return optionalUser.orElse(null);
    }
}