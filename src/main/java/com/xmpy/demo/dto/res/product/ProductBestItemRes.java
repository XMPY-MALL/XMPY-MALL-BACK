package com.xmpy.demo.dto.res.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
    public class ProductBestItemRes {

        private Long productId;
        private String categoryDetailName;
        private String productName;
        private Integer price;
        private String thumbnailUrl;
        private Boolean best;
        private Boolean soldOut;
        private Integer reviewCount;

    }

