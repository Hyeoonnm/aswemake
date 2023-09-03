package com.aswemake.api;

import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/product/api")
@RestController
@RequiredArgsConstructor
public class ProductApi {
    // 생성, 수정, 삭제는 마트의 권한이 필요하다.
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ProductDTO productDTO) {
        productDTO.setCreateDate(LocalDateTime.now());
        productService.save(productDTO);
        return ResponseEntity.ok().body("상품 등록");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ProductDTO findProduct = productService.findById(id);
        if (findProduct != null) {
            dto.setCreateDate(findProduct.getCreateDate());
            dto.setModifiedDate(LocalDateTime.now());

            findProduct.setPrice(dto.getPrice());
            findProduct.setName(dto.getName());
            findProduct.setCreateDate(dto.getCreateDate());
            findProduct.setModifiedDate(dto.getModifiedDate());
            productService.save(findProduct);
            return ResponseEntity.ok().body("상품 수정 완료");
        } else {
            return ResponseEntity.badRequest().body("상품 수정 실패");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().body("상품 삭제");
    }

    @GetMapping("/prev/{id}")
    public ResponseEntity<List<PrevProductInfoDTO>> list(@PathVariable Long id) {
        List<PrevProductInfoDTO> list = productService.findPrevProduct(id);
        return ResponseEntity.ok().body(list);
    }
}
