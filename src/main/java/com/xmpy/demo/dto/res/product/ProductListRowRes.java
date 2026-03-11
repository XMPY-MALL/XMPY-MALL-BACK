package com.xmpy.demo.dto.res.product;

// 프론트에서 카드 리스트 뿌리기용

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListRowRes {

    // 상품 Id
    private long productId;
    // 상품명
    private String productName;
    // 가격
    private int price;
    // 베스트 여부(체크박스로 true, false)
    private boolean best;
}
