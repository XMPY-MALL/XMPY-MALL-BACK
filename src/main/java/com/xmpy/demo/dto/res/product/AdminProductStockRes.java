package com.xmpy.demo.dto.res.product;

import com.xmpy.demo.entity.Product;
import com.xmpy.demo.entity.ProductThumbnail;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AdminProductStockRes {
    private Long    productId;
    private String  productName;
    private Integer price;
    private Long    categoryId;
    private String  imgUrl;

    public static AdminProductStockRes from(Product product) {
        String imgUrl = product.getProductThumbnails().stream()
                .findFirst()
                .map(ProductThumbnail::getProductUrl)
                .orElse(null);

        return AdminProductStockRes.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .imgUrl(imgUrl)
                .build();
    }
}