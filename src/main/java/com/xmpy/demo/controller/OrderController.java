package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.order.OrderCreateReq;
import com.xmpy.demo.dto.res.order.OrderResDto;
import com.xmpy.demo.jwt.JwtUtil;
import com.xmpy.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @GetMapping("/me")
    public ResponseEntity<List<OrderResDto>> getMyOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        String email = authentication.getName();

        // user_id를 토큰에서 뽑아서 들고 오기
        // order은 계속 오류가 나기때문에 user_id를 가지고 조회를 해보는 걸로 해보자

        return ResponseEntity.ok(orderService.getOrdersByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(
            @RequestBody OrderCreateReq dto,
            @RequestHeader("Authorization") String authHeader) {

        String token = jwtUtil.removeBearer(authHeader);
        long userId = jwtUtil.getUserId(token);

        orderService.placeOrder(userId, dto);
        return ResponseEntity.ok().build();
    }
}
