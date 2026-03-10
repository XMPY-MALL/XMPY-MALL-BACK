package com.xmpy.demo.dto.req.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAddReq {

    private String productName;
    private String description;
    private int price;
    private List<String> imageUrls;
    private long selectedCategoryId;
    private long selectedSubCategoryId;
    private List<StockReq> stocks;
    private String detailContent;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StockReq {
        private String size;
        private String color;
        private int quantity;
    }
}
