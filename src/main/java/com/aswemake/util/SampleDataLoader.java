package com.aswemake.util;

import com.aswemake.dao.CouponDAO;
import com.aswemake.dao.MemberDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.entity.CouponEntity;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.ProductEntity;
import com.aswemake.entity.enums.CouponRange;
import com.aswemake.entity.enums.CouponType;
import com.aswemake.entity.enums.MemberRole;
import com.aswemake.service.member.MemberService;
import com.aswemake.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final CouponDAO couponDAO;
    private final MemberService memberService;
    private final MemberDAO memberDAO;
    private final ProductService productService;
    private final ProductDAO productDAO;
    private final OrderItemDAO orderItemDAO;

    @Override
    public void run(String... args) throws Exception {
        ProductDTO product1 = new ProductDTO();
        product1.setName("딸기");
        product1.setPrice(1000);
        product1.setCreateDate(LocalDateTime.now());

        ProductDTO product2 = new ProductDTO();
        product2.setName("사과");
        product2.setPrice(2000);
        product2.setCreateDate(LocalDateTime.now());

        ProductDTO product3 = new ProductDTO();
        product3.setName("바나나");
        product3.setPrice(3000);
        product3.setCreateDate(LocalDateTime.now());

        ProductEntity save = productDAO.save(ProductDTO.toEntity(product1));
        productDAO.save(ProductDTO.toEntity(product2));
        productDAO.save(ProductDTO.toEntity(product3));

        save.setName("딸기 -> 청포도");
        save.setPrice(2000);
        ProductDTO update = productService.update(save.getId(), ProductEntity.toDTO(save));
        update.setName("복숭아");
        update.setPrice(3000);
        productService.update(update.getId(), update);

        MemberDTO user = new MemberDTO();
        user.setLoginId("user");
        user.setPassword("1234");
        user.setRole(MemberRole.USER);

        MemberDTO admin = new MemberDTO();
        admin.setLoginId("admin");
        admin.setPassword("1234");
        admin.setRole(MemberRole.ADMIN);
        memberService.signup(user);
        memberService.signup(admin);

        OrderItemDTO orderItem1 = new OrderItemDTO();
        orderItem1.setMemberId(1L);
        orderItem1.setProductId(1L);
        orderItem1.setCount(3);

        OrderItemDTO orderItem2 = new OrderItemDTO();
        orderItem2.setMemberId(1L);
        orderItem2.setProductId(2L);
        orderItem2.setCount(1);

        OrderItemDTO orderItem3 = new OrderItemDTO();
        orderItem3.setMemberId(1L);
        orderItem3.setProductId(3L);
        orderItem3.setCount(2);

        MemberEntity memberById = memberDAO.findMemberById(1L);
        ProductEntity productById1 = productDAO.findProductById(1L);
        ProductEntity productById2 = productDAO.findProductById(2L);
        ProductEntity productById3 = productDAO.findProductById(3L);

        orderItemDAO.save(OrderItemDTO.toEntity(orderItem1,memberById,productById1));
        orderItemDAO.save(OrderItemDTO.toEntity(orderItem2,memberById,productById2));
        orderItemDAO.save(OrderItemDTO.toEntity(orderItem3,memberById,productById3));

        CouponEntity wholeFix = CouponEntity.builder()
                .name("wholeFix")
                .couponRange(CouponRange.whole)
                .couponType(CouponType.fix)
                .build();

        CouponEntity wholeProportion = CouponEntity.builder()
                .name("wholeProportion")
                .couponRange(CouponRange.whole)
                .couponType(CouponType.proportion)
                .build();

        CouponEntity specificFix = CouponEntity.builder()
                .name("specificFix")
                .couponRange(CouponRange.specific)
                .couponType(CouponType.fix)
                .build();

        CouponEntity specificProportion = CouponEntity.builder()
                .name("specificProportion")
                .couponRange(CouponRange.specific)
                .couponType(CouponType.proportion)
                .build();

        couponDAO.save(wholeFix);
        couponDAO.save(wholeProportion);
        couponDAO.save(specificFix);
        couponDAO.save(specificProportion);

    }
}