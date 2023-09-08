package com.aswemake.api;

import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> list() {
        List<ProductDTO> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO productDTO) {
        ProductDTO save = productService.save(productDTO);
        return ResponseEntity.ok().body(save);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto.setId(id);

        ProductDTO update = productService.update(id, dto);
        return ResponseEntity.ok().body("상품 수정 완료");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().body("상품 삭제");
    }

    @GetMapping("/prev/{id}")
    public ResponseEntity<List<PrevProductInfoDTO>> list(@PathVariable Long id) {
        List<PrevProductInfoDTO> list = productService.findPrevProduct(id);
        for (PrevProductInfoDTO x :
                list) {
            x.setId(id);
        }
        return ResponseEntity.ok().body(list);
    }
}
