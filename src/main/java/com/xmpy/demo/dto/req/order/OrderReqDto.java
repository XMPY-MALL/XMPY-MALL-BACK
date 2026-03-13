package com.xmpy.demo.dto.req.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderReqDto {
    private int totalPrice;
    private String address;
    private List<OrderItemDTO> orderItems;

    @Getter
    @NoArgsConstructor
    public static class OrderItemDTO {
        private long productId;
        private long colorId;
        private long sizeId;
        private int quantity;
        private int price;
    }
}