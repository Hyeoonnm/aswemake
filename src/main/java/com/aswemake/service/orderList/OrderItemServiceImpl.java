package com.aswemake.service.orderList;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.ProductDTO;
import com.aswemake.dto.repOrderItemDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.OrderItemEntity;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDAO orderListDAO;
    private final MemberDAO memberDAO;
    private final ProductDAO productDAO;

    @Override
    public OrderItemDTO save(OrderItemDTO dto) {
        MemberEntity member = memberDAO.findMemberById(dto.getMemberId());
        ProductEntity product = productDAO.findProductById(dto.getProductId());
        OrderItemEntity entity = OrderItemDTO.toEntity(dto, member, product);

        OrderItemEntity save = orderListDAO.save(entity);
        int count = save.getCount();
        return OrderItemEntity.toDTO(product, member, count);
    }

    @Override
    public void delete(Long memberId, Long productId) {
        orderListDAO.deleteByMemberIdAndProductId(memberId, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public repOrderItemDTO findAllByMemberId(Long memberId) {
        List<OrderItemEntity> allByMemberId = orderListDAO.findAllByMemberId(memberId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();

        int index = 0;
        for (OrderItemEntity x :
                allByMemberId) {
            Long productId = x.getProduct().getId();
            ProductEntity productById = productDAO.findProductById(productId);
            productDTOList.add(ProductEntity.toDTO(productById));
            cnt.add(allByMemberId.get(index).getCount());
            index++;
        }

        return repOrderItemDTO.builder()
                .product(productDTOList)
                .count(cnt)
                .delivery(2500)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public int total(Long memberId) {
        List<OrderItemEntity> allByMemberId = orderListDAO.findAllByMemberId(memberId);
        int total = 0;
        for (OrderItemEntity x:
             allByMemberId) {
            total += x.getProduct().getPrice() * x.getCount();
        }
        return total;

    }
}
