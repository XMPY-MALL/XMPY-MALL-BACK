package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.order.OrderReqDto;
import com.xmpy.demo.entity.OrderItem;
import com.xmpy.demo.entity.Orders;
import com.xmpy.demo.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional
    public void createOrder(long userId, OrderReqDto req) {
        Orders orders = Orders.builder()
                .userId(userId)
                .status("PENDING")
                .totalPrice(req.getTotalPrice())
                .address(req.getAddress())
                .build();

        orderMapper.insertOrder(orders); // useGeneratedKeys로 ordersId 채워짐

        List<OrderItem> orderItems = req.getOrderItems().stream()
                .map(item -> new OrderItem(
                        0L,
                        orders.getOrdersId(),
                        item.getProductId(),
                        item.getColorId(),
                        item.getSizeId(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        orderMapper.insertOrderItems(orderItems);
    }
}