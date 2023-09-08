package com.aswemake.api;

import com.aswemake.dto.CouponDTO;
import com.aswemake.service.coupon.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CouponApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CouponService couponService;

    @Test
    @DisplayName("전체 상품 비율 할인 쿠폰 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void whole_proportion() throws Exception {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName("wholeProportion");
        couponDTO.setMemberId(1L);

        String jsonData = objectMapper.writeValueAsString(couponDTO);

        when(couponService.whole(any(), any()))
                .thenReturn(1000);
        mockMvc.perform(post("/coupon/api/whole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("전체 상품 고정 할인 쿠폰 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void whole_fix() throws Exception {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName("wholeFix");
        couponDTO.setMemberId(1L);

        String jsonData = objectMapper.writeValueAsString(couponDTO);

        when(couponService.whole(any(), any()))
                .thenReturn(1000);
        mockMvc.perform(post("/coupon/api/whole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("개별 상품 비율 할인 쿠폰 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void specific_proportion() throws Exception {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName("specificProportion");
        couponDTO.setMemberId(1L);

        String jsonData = objectMapper.writeValueAsString(couponDTO);

        when(couponService.whole(any(), any()))
                .thenReturn(1000);
        mockMvc.perform(post("/coupon/api/specific")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("개별 상품 고정 할인 쿠폰 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void specific_fix() throws Exception {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponName("specificFix");
        couponDTO.setMemberId(1L);

        String jsonData = objectMapper.writeValueAsString(couponDTO);

        when(couponService.whole(any(), any()))
                .thenReturn(1000);
        mockMvc.perform(post("/coupon/api/specific")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }
}