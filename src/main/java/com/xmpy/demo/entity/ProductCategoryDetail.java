package com.xmpy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductCategoryDetail {
    private long categoryDetailId;
    private long categoryId;
    private String categoryDetailName;
}
