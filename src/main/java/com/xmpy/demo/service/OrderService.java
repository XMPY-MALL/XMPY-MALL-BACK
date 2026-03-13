package com.xmpy.demo.service;

import com.xmpy.demo.dto.res.order.OrderResDto;
import com.xmpy.demo.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    // 해당 부분에서 주문내역 확인 할 거임
    private final OrderMapper orderMapper;

    public List<OrderResDto> getOrdersByEmail(String email) {
        return orderMapper.findOrdersByEmail(email);
    }
}
