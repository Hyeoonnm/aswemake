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
        member.setName("user");
        member.setPassword("1111");
        member.setRole("USER");
        MemberDTO signup = memberService.signup(member);

        assertThat("user").isEqualTo(signup.getName());
        boolean matches = encoder.matches("1111", signup.getPassword());
        assertTrue(matches);
        assertThat("ROLE_USER").isEqualTo(signup.getRole());
    }
}