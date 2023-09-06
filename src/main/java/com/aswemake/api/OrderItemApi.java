package com.aswemake.api;

import com.aswemake.dto.OrderItemDTO;
import com.aswemake.dto.repOrderItemDTO;
import com.aswemake.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/orderList/api")
@RestController
@RequiredArgsConstructor
public class OrderItemApi {
    private final OrderListService orderListService;

    @PostMapping("/list")
    public ResponseEntity<repOrderItemDTO> list(@RequestBody Map<String, Long> req) {
        Long id = req.get("id");
        repOrderItemDTO rep = orderListService.findAllByMemberId(id);
        if (rep.getProduct().size() < 1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rep);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderItemDTO> add(@RequestBody OrderItemDTO dto) {
        System.out.println(dto);
        OrderItemDTO save = orderListService.save(dto);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long memberId, Long productId) {
        orderListService.delete(memberId, productId);
        return ResponseEntity.ok("상품 삭제");
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> total(Long memberId) {
        int total = orderListService.total(memberId);
        return ResponseEntity.ok(total);
    }
}
