package com.xmpy.demo.view;

import lombok.Data;

@Data
public class ProductSubMenu {
    private Long productId;
    private String productName;
    private int price;
    private boolean best;
    private Long categoryDetailId;
    private String categoryDetailName;
    private String thumbnailUrl;
    private boolean soldOut;
}