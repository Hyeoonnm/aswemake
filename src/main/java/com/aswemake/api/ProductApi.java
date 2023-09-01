package com.aswemake.api;

import com.aswemake.dto.MemberDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.entity.enums.MemberEnum;
import com.aswemake.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product/v1/api")
@RestController
@RequiredArgsConstructor
public class ProductApi {
    // 생성, 수정, 삭제는 마트의 권한이 필요하다.
    private final ProductService productService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> add(@RequestBody ProductDTO productDTO) {
        productService.add(productDTO);
        return ResponseEntity.ok().body("상품 등록 완료");
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> update() {
        return ResponseEntity.ok().body("업데이트 완료");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok().body("삭제 완료");
    }
}
