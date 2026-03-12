package com.xmpy.demo.dto.res.product;

import com.xmpy.demo.view.ProductSubMenu;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class SubMenuResDto {
    private String categoryDetailName;
    private int totalPages;
    private List<SubMenuProductResDto> products;

    @Getter
    @Builder
    public static class SubMenuProductResDto {
        private Long productId;
        private String categoryDetailName;
        private String productName;
        private int price;
        private String thumbnailUrl;
        private boolean best;
        private boolean soldOut;
        private int reviewCount;

        public static SubMenuProductResDto from(ProductSubMenu p, int reviewCount) {
            return SubMenuProductResDto.builder()
                    .productId(p.getProductId())
                    .categoryDetailName(p.getCategoryDetailName())
                    .productName(p.getProductName())
                    .price(p.getPrice())
                    .thumbnailUrl(p.getThumbnailUrl())
                    .best(p.isBest())
                    .soldOut(p.isSoldOut())
                    .reviewCount(reviewCount)
                    .build();
        }
    }


}