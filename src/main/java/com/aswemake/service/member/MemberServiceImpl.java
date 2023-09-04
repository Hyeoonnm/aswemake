package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long signup(MemberDTO member) throws Exception {
        if (memberDAO.findByUsername(member.getUsername()).isPresent()) {
            return null;
        }

        MemberEntity memberEntity = MemberDTO.toEntity(member);
        MemberEntity saveMember = memberDAO.save(memberEntity);
        saveMember.encodePassword(encoder);

        return 1L;
    }

    @Override
    public String login(MemberDTO memberDTO) {
        MemberEntity member = memberDAO.findByUsername(memberDTO.getUsername())
                .orElse(null);
        if (member == null) {
            return null;
        }

        List<String> roles = new ArrayList<>();
        roles.add(String.valueOf(member.getRole()));

        return jwtTokenProvider.createToken(member.getUsername(), roles);
    }
}