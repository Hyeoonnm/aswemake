package com.aswemake.api;

import com.aswemake.dto.ProductDTO;
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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;


    @Test
    @DisplayName("상품 목록 조회 [unknown]")
    @WithMockUser(username = "user", authorities = {})
    void list_unKnown() throws Exception {
        mockMvc.perform(get("/product/api/list"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 목록 조회 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void list_user() throws Exception {
        mockMvc.perform(get("/product/api/list"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 목록 조회 [ADMIN]")
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void list_admin() throws Exception {
        mockMvc.perform(get("/product/api/list"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 저장 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void add_user() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("상품");
        dto.setPrice(1000);

        String jsonData = objectMapper.writeValueAsString(dto);

        when(productService.save(any()))
                .thenReturn(dto);

        mockMvc.perform(post("/product/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 저장 [ADMIN]")
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void add_Admin() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("상품");
        dto.setPrice(1000);

        String jsonData = objectMapper.writeValueAsString(dto);

        when(productService.save(any()))
                .thenReturn(dto);

        mockMvc.perform(post("/product/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void update_user() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("상품");
        dto.setPrice(1000);
        dto.setCreateDate(LocalDateTime.now());
        when(productService.save(any()))
                .thenReturn(dto);

        dto.setName("상품 수정");
        dto.setPrice(2000);

        String jsonData = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/product/api/update/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 [ADMIN]")
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void update_admin() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("상품");
        dto.setPrice(1000);
        dto.setCreateDate(LocalDateTime.now());
        when(productService.save(any()))
                .thenReturn(dto);

        dto.setName("상품 수정");
        dto.setPrice(2000);
        String jsonData = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/product/api/update/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 [USER]")
    @WithMockUser(username = "user", authorities = "USER")
    void delete_user() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("상품");
        dto.setPrice(1000);
        dto.setCreateDate(LocalDateTime.now());
        when(productService.save(any()))
                .thenReturn(dto);

        mockMvc.perform(delete("/product/api/delete/" + dto.getId()))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 [ADMIN]")
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void delete_admin() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("상품");
        dto.setPrice(1000);
        dto.setCreateDate(LocalDateTime.now());
        when(productService.save(any()))
                .thenReturn(dto);

        mockMvc.perform(delete("/product/api/delete/" + dto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("수정 내역 조회")
    @WithMockUser(username = "user", authorities = "USER")
    void update_info_list() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("상품");
        dto.setPrice(1000);
        dto.setCreateDate(LocalDateTime.now());
        when(productService.save(any()))
                .thenReturn(dto);
        when(productService.update(any(),any()))
                .thenReturn(dto);

        mockMvc.perform(get("/product/api/prev/" + dto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}