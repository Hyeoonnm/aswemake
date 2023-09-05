package com.aswemake.service;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.dao.ProductDAO;
import com.aswemake.dto.OrderItemDTO;
import com.aswemake.entity.MemberEntity;
import com.aswemake.entity.OrderItemEntity;
import com.aswemake.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {
    private final OrderItemDAO orderListDAO;
    private final MemberDAO memberDAO;
    private final ProductDAO productDAO;
    @Override
    public OrderItemDTO save(OrderItemDTO dto) {
        MemberEntity member = memberDAO.findMemberById(dto.getMemberId());
        ProductEntity product = productDAO.findProductById(dto.getProductId());

        OrderItemEntity entity = OrderItemDTO.toEntity(dto, member, product);
        orderListDAO.save(entity);

        return OrderItemEntity.toDTO(entity);
    }

    @Override
    public List<OrderItemDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Long memberId, Long productId) {
        orderListDAO.deleteByMemberIdAndProductId(memberId,productId);
    }
}
