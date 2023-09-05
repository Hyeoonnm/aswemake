package com.aswemake.api;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orderList/api")
@RestController
@RequiredArgsConstructor
public class OrderItemApi {
    private final OrderListService orderListService;

    @GetMapping("/list")
    public ResponseEntity<List<OrderItemDTO>> list() {
        List<OrderItemDTO> list = orderListService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderItemDTO> add(OrderItemDTO dto) {
        OrderItemDTO save = orderListService.save(dto);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long memberId, Long productId) {
        orderListService.delete(memberId, productId);
        return ResponseEntity.ok("상품 삭제");
    }
}
