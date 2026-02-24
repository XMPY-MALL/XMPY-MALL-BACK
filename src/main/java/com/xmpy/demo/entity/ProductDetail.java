package com.xmpy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDetail {
    private long productDetailId;
    private long productId;
    private long stockId;
    private String productDetailContent;
}
