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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품 등록 (성공) 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addWithAdmin() throws Exception {
        ProductDTO product = new ProductDTO();
        product.setName("테스트 상품");
        product.setPrice(1);
        product.setCreateDate(LocalDateTime.now());

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 등록 (실패) 테스트")
    @WithMockUser(username = "user", roles = "USER")
    void addWithUser() throws Exception {
        ProductDTO product = new ProductDTO();
        product.setName("테스트 상품");
        product.setPrice(1);
        product.setCreateDate(LocalDateTime.now());

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void update() throws Exception {
        ProductDTO product = new ProductDTO();
        product.setId(6L);
        product.setName("상품 수정 2차");
        product.setPrice(10000);

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/product/api/update/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void delete() throws Exception {
        ProductDTO product = new ProductDTO();
        product.setId(1L);

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.delete("/product/api/delete/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 리스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void prevProductInfo() throws Exception {

        ProductDTO product = new ProductDTO();
        product.setId(1L);

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/api/prev/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
}