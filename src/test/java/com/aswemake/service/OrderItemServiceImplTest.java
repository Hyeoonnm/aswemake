package com.aswemake.service;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.dto.repOrderItemDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.OrderItemEntity;
import com.aswemake.entity.ProductEntity;
import com.aswemake.service.orderList.OrderItemService;
import com.aswemake.service.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderItemServiceImplTest {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private ProductDAO productDAO;

    @Test
    @DisplayName("주문 목록에 저장하기")
    void save() {
        MemberEntity saveMember = getMemberEntity();

        ProductEntity saveProduct = getProductEntity();
        // 어떤 멤버가
        // 어떤 상품을 저장하는지
        OrderItemDTO saveOrderItem = getOrderItemDTO(saveMember, saveProduct);

        // 저장된 상품의 정보 확인
        assertThat(saveOrderItem.getMemberId()).isEqualTo(saveMember.getId());
        assertThat(saveOrderItem.getProductId()).isEqualTo(saveProduct.getId());
        assertThat(saveOrderItem.getCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("주문 목록 삭제하기")
    void delete() {
        MemberEntity saveMember = getMemberEntity();

        ProductEntity saveProduct = getProductEntity();
        // 주문목록에 저장
        OrderItemDTO saveOrderItem = getOrderItemDTO(saveMember, saveProduct);

        // 삭제
        orderItemService.delete(saveOrderItem.getMemberId(), saveOrderItem.getProductId());

        // 삭제 후 해당 값이 존재하는지 안하는지
        // 삭제 했기 때문에 null로 확인
        assertNull(orderItemDAO.findByMemberIdAndProductId(saveOrderItem.getMemberId(), saveOrderItem.getProductId()));
    }

    @Test
    @DisplayName("멤버아이디로 주문 목록 조회하기")
    void findAllByMemberId() {
        MemberEntity saveMember = getMemberEntity();

        // 주문 목록에 담을 여러가지 상품들
        List<ProductEntity> productList = getProductEntities();

        // 상품들을 저장
        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        // 상품들을 주문 목록에 저장하기 위해 dto로 반환
        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 주문 목록에 저장
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
        }

        // 멤버 id로 주문 목록 조회
        repOrderItemDTO allByMemberId = orderItemService.findAllByMemberId(saveMember.getId());

        // 주문 목록에 저장을 시도한 size와
        // db에 담긴 size의 크기 비교
        assertThat(allByMemberId.getProduct().size()).isEqualTo(dtoList.size());
    }

    @Test
    @DisplayName("주문 목록 총 가격")
    void total() {
        MemberEntity saveMember = getMemberEntity();

        List<ProductEntity> productList = getProductEntities();

        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 비교하기 위한 total 값
        int total = 0;
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
            ProductDTO byId = productService.findById(x.getProductId());
            // 내가 주입하려는 상품의 총 가격 total에 저장
            total += byId.getPrice() * x.getCount();
        }
        // db에 담긴 상품들의 총 가격
        int outPutTotal = orderItemService.total(saveMember.getId());

        // 비교하기 위한 total 값과 db에 담긴 상품들의 가격 비교
        assertThat(outPutTotal).isEqualTo(total);
    }

    private OrderItemDTO getOrderItemDTO(MemberEntity saveMember, ProductEntity saveProduct) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setMemberId(saveMember.getId());
        dto.setProductId(saveProduct.getId());
        dto.setCount(5);
        return orderItemService.save(dto);
    }

    private ProductEntity getProductEntity() {
        ProductEntity product = ProductEntity.builder()
                .name("상품")
                .price(1000)
                .build();
        return productDAO.save(product);
    }

    private MemberEntity getMemberEntity() {
        MemberEntity member = MemberEntity.builder()
                .loginId("user")
                .password("user")
                .build();
        return memberDAO.save(member);
    }

    private static List<OrderItemDTO> getOrderItemDTOList(MemberEntity saveMember, List<ProductEntity> productEntities) {
        List<OrderItemDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < productEntities.size(); i++) {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setMemberId(saveMember.getId());
            dto.setProductId(productEntities.get(i).getId());
            dto.setCount(i+3);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private static List<ProductEntity> getProductEntities() {
        List<ProductEntity> productList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            ProductEntity product = ProductEntity.builder()
                    .name("상품" + i)
                    .price(1000 * i)
                    .build();
            productList.add(product);
        }
        return productList;
    }
}