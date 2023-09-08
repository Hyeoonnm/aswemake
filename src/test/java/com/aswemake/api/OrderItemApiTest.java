package com.aswemake.api;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.service.member.MemberService;
import com.aswemake.service.orderList.OrderItemService;
import com.aswemake.service.product.ProductService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderItemApiTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderItemService orderItemService;
    @MockBean
    private ProductService productService;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("주문 목록 조회 [User]")
    @WithMockUser(username = "user", authorities = "USER")
    void list() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setMemberId(1L);
        orderItemDTO.setCount(5);

        when(orderItemService.save(any()))
                .thenReturn(orderItemDTO);

        mockMvc.perform(get("/orderList/api/list/" + orderItemDTO.getMemberId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("주문 목록 추가 [User]")
    @WithMockUser(username = "user", authorities = "USER")
    void add() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setMemberId(1L);
        orderItemDTO.setCount(5);
        when(orderItemService.save(any()))
                .thenReturn(orderItemDTO);

        String jsonData = objectMapper.writeValueAsString(orderItemDTO);

        mockMvc.perform(post("/orderList/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("주문 목록 삭제 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void delete_user() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setMemberId(1L);
        orderItemDTO.setCount(10);

        when(orderItemService.save(any()))
                .thenReturn(orderItemDTO);

        mockMvc.perform(delete("/orderList/api/delete/" + orderItemDTO.getMemberId() + "/" + orderItemDTO.getProductId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("주문 목록 총 가격 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void total() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setMemberId(1L);
        orderItemDTO.setCount(10);

        when(orderItemService.save(any()))
                .thenReturn(orderItemDTO);

        mockMvc.perform(get("/orderList/api/total/" + orderItemDTO.getMemberId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}