package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.order.AdminOrderUpdateReq;
import com.xmpy.demo.dto.req.review.AdminReviewUpdateReq;
import com.xmpy.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/mypage")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/orders")
    public ResponseEntity<?> getAdminOrders() {
        return ResponseEntity.ok(adminService.getAdminOrders());
    }

    @PatchMapping("/orders/{ordersId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable long ordersId,
                                               @RequestBody AdminOrderUpdateReq req) {
        adminService.updateOrderStatus(ordersId, req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getAdminReviews() {
        return ResponseEntity.ok(adminService.getAdminReviews());
    }

    @PatchMapping("/reviews/{reviewId}/reply")
    public ResponseEntity<?> updateAdminReviewReply(@PathVariable long reviewId,
                                                    @RequestBody AdminReviewUpdateReq req) {
        adminService.updateAdminReviewReply(reviewId, req);
        return ResponseEntity.ok().build();
    }
}