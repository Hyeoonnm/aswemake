package com.aswemake.api;

import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.enums.MemberRole;
import com.aswemake.service.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder encoder;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void signup() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setLoginId("user");
        member.setPassword(encoder.encode("user"));
        member.setRole(MemberRole.USER);

        String jsonData = objectMapper.writeValueAsString(member);

        when(memberService.signup(any()))
                .thenReturn(member);

        mockMvc.perform(post("/member/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }
}