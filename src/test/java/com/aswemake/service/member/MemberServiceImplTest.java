package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    void deleteAll() {
        memberDAO.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void signup() {
        MemberDTO member = new MemberDTO();
        member.setName("테스트 유저");
        member.setPassword("1234");
        member.setRole("USER");
        MemberDTO signup = memberService.signup(member);

        assertThat("테스트 유저").isEqualTo(signup.getName());
        boolean matches = encoder.matches("1234", signup.getPassword());
        assertTrue(matches);
        assertThat("ROLE_USER").isEqualTo(signup.getRole());
    }
}