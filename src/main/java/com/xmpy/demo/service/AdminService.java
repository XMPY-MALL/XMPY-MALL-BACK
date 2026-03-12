package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.order.AdminOrderUpdateReq;
import com.xmpy.demo.dto.req.review.AdminReviewUpdateReq;
import com.xmpy.demo.dto.res.order.OrderRes;
import com.xmpy.demo.dto.res.review.AdminReviewRes;
import com.xmpy.demo.mapper.OrderMapper;
import com.xmpy.demo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrderMapper orderMapper;
    private final ReviewMapper reviewMapper;

    public List<OrderRes> getAdminOrders() {
        return orderMapper.findAdminOrders();
    }

    public void updateOrderStatus(long ordersId, AdminOrderUpdateReq req) {
        orderMapper.updateOrderStatus(ordersId, req.getStatus());
    }

    public List<AdminReviewRes> getAdminReviews() {
        return reviewMapper.findAdminReviews();
    }

    public void updateAdminReviewReply(long reviewId, AdminReviewUpdateReq req) {
        reviewMapper.updateAdminReply(reviewId, req.getAdminReply());
    }
}