package com.aswemake.api;

import com.aswemake.dto.MemberDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원 가입")
    public void testSignup() throws Exception {
        MemberDTO member = new MemberDTO();
        member.setUsername("user");
        member.setPassword("1111");
        member.setRole("USER");

        String memberJson = objectMapper.writeValueAsString(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/member/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(memberJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
}