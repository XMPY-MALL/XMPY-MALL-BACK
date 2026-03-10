package com.xmpy.demo.controller;

import com.xmpy.demo.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/sizes")
    public ResponseEntity<?> sizes() {
        return ResponseEntity.ok(stockService.getSizes());
    }

    @GetMapping("/colors")
    public ResponseEntity<?> colors() {
        return ResponseEntity.ok(stockService.getColors());
    }

    // 재고관리 리스트
    @GetMapping("/admin/products/{productId}/stocks")
    public ResponseEntity<?> list(@PathVariable long productId) {
        try {
            return ResponseEntity.ok(stockService.getStocksByProduct(productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // 옵션 추가
    @PostMapping("/admin/products/{productId}/stocks")
    public ResponseEntity<?> add(@PathVariable long productId, @RequestBody Map<String, Object> body) {
        try {
            Long sizeId = body.get("sizeId") == null ? null : ((Number) body.get("sizeId")).longValue();
            Long colorId = body.get("colorId") == null ? null : ((Number) body.get("colorId")).longValue();
            int count = body.get("count") == null ? 0 : ((Number) body.get("count")).intValue();

            stockService.addStock(productId, sizeId, colorId, count);
            return ResponseEntity.ok(Map.of("message", "옵션 추가 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // 재고 수정
    @PutMapping("/admin/stocks/{stockId}")
    public ResponseEntity<?> update(@PathVariable long stockId, @RequestBody Map<String, Object> body) {
        try {
            int count = body.get("count") == null ? 0 : ((Number) body.get("count")).intValue();
            stockService.updateStock(stockId, count);
            return ResponseEntity.ok(Map.of("message", "재고 수정 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // 옵션 삭제
    @DeleteMapping("/admin/stocks/{stockId}")
    public ResponseEntity<?> delete(@PathVariable long stockId) {
        try {
            stockService.deleteStock(stockId);
            return ResponseEntity.ok(Map.of("message", "옵션 삭제 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/admin/stocks/decrease")
    public ResponseEntity<?> decrease(@RequestBody Map<String, Object> body) {
        try {
            long productId = ((Number) body.get("productId")).longValue();
            long sizeId = ((Number) body.get("sizeId")).longValue();
            long colorId = ((Number) body.get("colorId")).longValue();
            int quantity = ((Number) body.get("quantity")).intValue();

            stockService.decreaseStock(productId, sizeId, colorId, quantity);
            return ResponseEntity.ok(Map.of("message", "재고 차감 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/admin/stocks/restore")
    public ResponseEntity<?> restore(@RequestBody Map<String, Object> body) {
        try {
            long productId = ((Number) body.get("productId")).longValue();
            long sizeId = ((Number) body.get("sizeId")).longValue();
            long colorId = ((Number) body.get("colorId")).longValue();
            int quantity = ((Number) body.get("quantity")).intValue();

            stockService.restoreStock(productId, sizeId, colorId, quantity);
            return ResponseEntity.ok(Map.of("message", "재고 복구 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}