package com.xmpy.demo.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long productId;
    private long categoryId;
    private long categoryDetailId;
    private String productName;
    private String description;
    private boolean best;
    private int price;
    private String quickRundown;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private List<ProductThumbnail> productThumbnails;
    private ProductDetail productDetail;

}
