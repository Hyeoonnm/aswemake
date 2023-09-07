package com.aswemake.service.product;

import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.PrevProductInfoDTO;
import com.aswemake.dto.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDAO productDAO;

    @Test
    @DisplayName("상품 생성")
    void save() {
        ProductDTO dto = new ProductDTO();
        dto.setName("상품");
        dto.setPrice(1000);
        ProductDTO save = productService.save(dto);

        assertThat(dto.getName()).isEqualTo(save.getName());
    }

    @Test
    @DisplayName("상품 업데이트")
    void update() {
        ProductDTO dto = new ProductDTO();
        dto.setName("업데이트 할 상품");
        dto.setPrice(1000);
        ProductDTO save = productService.save(dto);

        ProductDTO byId = productService.findById(save.getId());

        byId.setPrice(2000); // 업데이트 하려는 가격
        ProductDTO update = productService.update(byId.getId(), byId); // 업데이트 하고 난 후의 가격

        // 업데이트 하려는 가격과 업데이트 하고 난 후의 가격을 비교
        assertThat(update.getPrice()).isEqualTo(byId.getPrice());
    }

    @Test
    @DisplayName("업데이트 내역 조회")
    @Transactional(readOnly = true)
    void findPrevProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setName("업데이트 내역 조회");
        dto.setPrice(1000); // 업데이트 하기 전의 가격

        ProductDTO save = productService.save(dto);

        ProductDTO byId = productService.findById(save.getId());

        byId.setPrice(2000); // 업데이트 하고 난 후의 가격 1회 업데이트
        ProductDTO update = productService.update(byId.getId(), byId);

        List<PrevProductInfoDTO> prevProduct = productService.findPrevProduct(update.getId());

        // 업데이트의 내역이 남는 것이기 때문에
        // 수정된 2000원이 들어가는 것이 아닌
        // 수정 되기 전의 가격인 1000원이 들어간다.
        assertThat(prevProduct.get(0).getPrice()).isEqualTo(1000);
    }

    @Test
    @DisplayName("상품 삭제")
    void delete() {
        ProductDTO dto = new ProductDTO();
        dto.setName("상품 삭제");
        dto.setPrice(1000);
        ProductDTO save = productService.save(dto);

        // 상품 저장 정보 확인
        List<ProductDTO> all = productService.findAll();

        // db에 저장된 갯수와
        // 내가 찾아온 저장된 정보의 갯수를 비교
        assertThat(productDAO.count()).isEqualTo(all.size());

        // 상품 정보 삭제
        productService.delete(save.getId());

        // 상품 정보 삭제 후 db와 갯수 비교
        // 한개가 삭제 되었기 떄문에 size - 1
        assertThat(productDAO.count()).isEqualTo(all.size()-1);
    }
}