package com.aswemake.api;

import com.aswemake.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private MockHttpSession session;
    @MockBean
    private ProductService productService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("상품 등록 (성공) 테스트")
    @WithMockUser(roles = "admin")
    void addWithAdmin() throws Exception {
        // 사용자 인증
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, String> map = new HashMap<>();
        map.put("name", "테스트 상품");
        map.put("price", "1000");
        map.put("createDate", String.valueOf(LocalDateTime.now()));

        mockMvc.perform(post("/product/v1/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 등록 (실패) 테스트")
    @WithMockUser(roles = "user")
    void addWithUser() throws Exception {
        // 사용자 인증
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, String> map = new HashMap<>();
        map.put("name", "테스트 상품");
        map.put("price", "1000");
        map.put("createDate", String.valueOf(LocalDateTime.now()));

        mockMvc.perform(post("/product/v1/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isForbidden()) // Expect a 403 Forbidden status
                .andDo(print());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}