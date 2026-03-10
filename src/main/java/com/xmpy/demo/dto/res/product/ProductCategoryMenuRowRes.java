package com.xmpy.demo.dto.res.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryMenuRowRes {
    private Long productCategoryId;
    private String productCategoryName;
    private Long productCategoryDetailId;
    private String productCategoryDetailName;
}
