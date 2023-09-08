package com.aswemake.service;

import com.aswemake.dao.CouponDAO;
import com.aswemake.dao.MemberDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.dto.OrderItemDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.ProductEntity;
import com.aswemake.service.coupon.CouponService;
import com.aswemake.service.orderList.OrderItemService;
import org.assertj.core.api.Assertions;
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
class CouponServiceImplTest {
    @Autowired
    private CouponService couponService;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private MemberDAO memberDAO;

    @Test
    @DisplayName("전체 상품에서 고정 가격 할인 쿠폰")
    void wholeFix() {
        MemberEntity saveMember = getMemberEntity();

        // 주문 목록에 담을
        // 1000원 짜리 한개
        // 2000원 짜리 2개
        List<ProductEntity> productList = getProductEntities();

        // 상품들을 저장
        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        // 상품들을 주문 목록에 저장하기 위해 dto로 반환
        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 주문 목록에 저장
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
        }

        // 전체 가격(5000)에서 고정 가격 3000원 할인
        int wholeFix = couponService.whole(saveMember.getId(), "wholeFix");

        assertThat(wholeFix).isEqualTo(2000);
    }

    @Test
    @DisplayName("전체 상품에서 30% 할인 쿠폰")
    void wholeProportion() {
        MemberEntity saveMember = getMemberEntity();

        // 주문 목록에 담을
        // 1000원 짜리 한개
        // 2000원 짜리 2개
        List<ProductEntity> productList = getProductEntities();

        // 상품들을 저장
        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        // 상품들을 주문 목록에 저장하기 위해 dto로 반환
        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 주문 목록에 저장
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
        }

        // 전체 가격(5000)에서  30% 할인
        int wholeFix = couponService.whole(saveMember.getId(), "wholeProportion");

        assertThat(wholeFix).isEqualTo(3500);
    }

    @Test
    @DisplayName("특정 상품 고정 가격 할인")
    void specificFix() {
        MemberEntity saveMember = getMemberEntity();

        // 주문 목록에 담을
        // 1000원 짜리 한개
        // 2000원 짜리 2개
        List<ProductEntity> productList = getProductEntities();

        // 상품들을 저장
        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        // 상품들을 주문 목록에 저장하기 위해 dto로 반환
        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 주문 목록에 저장
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
        }

        // 전체 가격(5000)에서 고정 가격 300원 할인 (개별 적용)
        // 2000 -> 1700 * 2 = 3400 + 1000 = 4400
        int specificFix = couponService.specific(saveMember.getId(), "specificFix", productEntities.get(1).getId());

        assertThat(specificFix).isEqualTo(4400);
    }

    @Test
    @DisplayName("특정 상품 비율 할인")
    void specificProportion() {
        MemberEntity saveMember = getMemberEntity();

        // 주문 목록에 담을
        // 1000원 짜리 한개
        // 2000원 짜리 2개
        List<ProductEntity> productList = getProductEntities();

        // 상품들을 저장
        List<ProductEntity> productEntities = productDAO.saveAll(productList);

        // 상품들을 주문 목록에 저장하기 위해 dto로 반환
        List<OrderItemDTO> dtoList = getOrderItemDTOList(saveMember, productEntities);

        // 주문 목록에 저장
        for (OrderItemDTO x: dtoList) {
            orderItemService.save(x);
        }

        // 전체 가격(5000)에서 고정 가격 10%할인 (개별 적용)
        // 2000 -> 1800 * 2 = 3600 + 1000 == 4600
        int specificFix = couponService.specific(saveMember.getId(), "specificProportion", productEntities.get(1).getId());

        assertThat(specificFix).isEqualTo(4600);
    }

    private MemberEntity getMemberEntity() {
        MemberEntity member = MemberEntity.builder()
                .loginId("user")
                .password("user")
                .build();
        return memberDAO.save(member);
    }

    private ProductEntity getProductEntity() {
        ProductEntity product = ProductEntity.builder()
                .name("상품")
                .price(1000)
                .build();
        return productDAO.save(product);
    }

    private OrderItemDTO getOrderItemDTO(MemberEntity saveMember, ProductEntity saveProduct) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setMemberId(saveMember.getId());
        dto.setProductId(saveProduct.getId());
        dto.setCount(5);
        return orderItemService.save(dto);
    }

    private static List<OrderItemDTO> getOrderItemDTOList(MemberEntity saveMember, List<ProductEntity> productEntities) {
        List<OrderItemDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < productEntities.size(); i++) {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setMemberId(saveMember.getId());
            dto.setProductId(productEntities.get(i).getId());
            dto.setCount(i+1);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private static List<ProductEntity> getProductEntities() {
        List<ProductEntity> productList = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            ProductEntity product = ProductEntity.builder()
                    .name("상품" + i)
                    .price(1000 * i)
                    .build();
            productList.add(product);
        }
        return productList;
    }
}