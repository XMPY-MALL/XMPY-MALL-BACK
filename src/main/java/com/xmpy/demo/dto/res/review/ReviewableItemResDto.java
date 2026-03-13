package com.xmpy.demo.dto.res.review;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewableItemResDto {
    private int orderItemId;
    private int ordersId;
    private int productId;
    private String productName;
    private String imgUrl;
    private String sizeName;
    private String colorName;
    private int quantity;
}
