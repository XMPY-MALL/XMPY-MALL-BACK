package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.order.OrderCreateReq;
import com.xmpy.demo.dto.res.order.OrderResDto;
import com.xmpy.demo.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    // 해당 부분에서 주문내역 확인 할 거임
    private final OrderMapper orderMapper;

    public List<OrderResDto> getOrdersByEmail(String email) {
        return orderMapper.findOrdersByEmail(email);
    }

    @Transactional
    public void placeOrder(long userId, OrderCreateReq dto) {
        orderMapper.insertOrder(userId, dto.getTotalPrice(), dto.getAddress());
        long ordersId = orderMapper.getLastInsertId();
        for (OrderCreateReq.OrderItem item : dto.getOrderItems()) {
            orderMapper.insertOrderItem(
                    ordersId,
                    item.getProductId(),
                    item.getSizeId(),
                    item.getColorId(),
                    item.getPrice(),
                    item.getQuantity()
            );
        }
    }
}
