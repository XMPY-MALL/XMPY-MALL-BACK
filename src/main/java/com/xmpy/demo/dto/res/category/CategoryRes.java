package com.xmpy.demo.dto.res.category;

import com.xmpy.demo.entity.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class CategoryRes {
    private Long productCategoryId;
    private String productCategoryName;
    private List<SubMenu> subMenu;

    @Getter
    @Builder
    public static class SubMenu {
        private Long productCategoryDetailId;
        private String productCategoryDetailName;
    }

    public static CategoryRes from(ProductCategory category) {
        return CategoryRes.builder()
                .productCategoryId(category.getCategoryId())
                .productCategoryName(category.getCategoryName())
                .subMenu(category.getDetails().stream()
                        .map(detail -> SubMenu.builder()
                                .productCategoryDetailId(detail.getCategoryDetailId())
                                .productCategoryDetailName(detail.getCategoryDetailName())
                                .build())
                        .toList())
                .build();
    }
}