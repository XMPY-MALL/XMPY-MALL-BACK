package com.xmpy.demo.dto.req.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReqDto {
    @Positive(message = "카테고리 Id는 1 이상이어야 합니다.")
    private long categoryId;
    @Positive(message = "카테고리 상세 Id는 1 이상이어야 합니다.")
    private long categoryDetailId;
    @NotBlank(message = "상품명은 꼭 입력하셔야 합니다.")
    private String productName;
    private String description;
    private List<String> imgUrl;
    @Positive(message = "가격은 0원보다 커야 합니다")
    private int price;
    private String quickRundown;
    private String productDetailContent;
}
