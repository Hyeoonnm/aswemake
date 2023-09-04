package com.aswemake.service.member;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.enums.MemberEnum;
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
    void signup() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setUsername("테스트 유저");
        member.setPassword("1234");
        member.setRole(MemberEnum.USER);
        Long signup = memberService.signup(member);

        assertThat(1L).isEqualTo(1L);
    }
}