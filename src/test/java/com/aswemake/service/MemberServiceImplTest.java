package com.aswemake.service;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.enums.MemberRole;
import com.aswemake.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDAO memberDAO;

    @Test
    @DisplayName("회원 가입")
    void signup() throws Exception {
        MemberDTO signup = getSignup();

        Optional<MemberEntity> byLoginId = memberDAO.findByLoginId(signup.getLoginId());

        assertEquals(signup.getLoginId(), byLoginId.get().getLoginId());
    }

    private MemberDTO getSignup() throws Exception {
        MemberDTO user = new MemberDTO();
        user.setLoginId("user");
        user.setPassword("user");
        user.setRole(MemberRole.USER);
        return memberService.signup(user);
    }
}