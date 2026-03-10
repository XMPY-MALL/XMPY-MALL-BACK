package com.xmpy.demo.dto.res.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductBestCategoryRes {
    private String categoryName;
    private List<ProductBestItemRes> products;
}
