package com.xmpy.demo.dto.res.order;

import lombok.Data;
import java.util.List;

@Data
public class OrderResDto {
    private Long ordersId;
    private String status;
    private int totalPrice;
    private String address;
    private String createdAt;
    private String estimatedDelivery;
    private List<OrderItemResDto> orderItems;
}
