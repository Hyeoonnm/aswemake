package com.aswemake.api;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.repOrderItemDTO;
import com.aswemake.service.orderList.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orderList/api")
@RestController
@RequiredArgsConstructor
public class OrderItemApi {
    private final OrderItemService orderItemService;

    @GetMapping("/list/{memberId}")
    public ResponseEntity<repOrderItemDTO> list(@PathVariable Long memberId) {
        repOrderItemDTO rep = orderItemService.findAllByMemberId(memberId);
        return ResponseEntity.ok(rep);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderItemDTO> add(@RequestBody OrderItemDTO dto) {
        OrderItemDTO save = orderItemService.save(dto);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete/{memberId}/{productId}")
    public ResponseEntity<String> delete(@PathVariable Long memberId, @PathVariable Long productId) {
        orderItemService.delete(memberId, productId);
        return ResponseEntity.ok("상품 삭제");
    }

    @GetMapping("/total/{memberId}")
    public ResponseEntity<Integer> total(@PathVariable Long memberId) {
        int total = orderItemService.total(memberId);
        return ResponseEntity.ok(total);
    }
}
