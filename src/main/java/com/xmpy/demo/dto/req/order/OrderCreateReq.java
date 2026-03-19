package com.xmpy.demo.dto.req.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateReq {
    private int totalPrice;
    private String address;
    private List<OrderItem> orderItems;

    @Data
    public static class OrderItem {
        private long productId;
        private long colorId;
        private long sizeId;
        private int quantity;
        private int price;
    }
}