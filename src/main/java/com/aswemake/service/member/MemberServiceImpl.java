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

    /**
     *  로그인 기능
     *  화면에서 loginId, password 입력받아 loginId와 password가 일치하면 User return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    @Transactional(readOnly = true)
    public MemberEntity login(MemberDTO dto) {
        Optional<MemberEntity> optionalUser = memberDAO.findByLoginId(dto.getLoginId());
        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        MemberEntity member = optionalUser.get();
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            return null;
        }
        return member;
    }

    /**
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
    @Transactional(readOnly = true)
    public MemberEntity getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<MemberEntity> optionalUser = memberDAO.findByLoginId(loginId);
        return optionalUser.orElse(null);
    }
}