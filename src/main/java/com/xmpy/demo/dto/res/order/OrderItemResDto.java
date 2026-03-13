package com.xmpy.demo.dto.res.order;

import lombok.Data;

@Data
public class OrderItemResDto {
    private Long orderItemId;
    private String productName;
    private String imgUrl;
    private String sizeName;
    private String colorName;
    private int price;
    private int quantity;
}
