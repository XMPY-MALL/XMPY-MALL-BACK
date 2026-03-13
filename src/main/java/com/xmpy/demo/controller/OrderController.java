package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.order.OrderReqDto;
import com.xmpy.demo.jwt.JwtUtil;
import com.xmpy.demo.service.OrderService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderReqDto req
    ) {
        try {
            Claims claims = jwtUtil.getClaims(jwtUtil.removeBearer(token));
            long userId = ((Number) claims.get("userId")).longValue();
            orderService.createOrder(userId, req);
            return ResponseEntity.ok(Map.of("message", "주문 완료"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
