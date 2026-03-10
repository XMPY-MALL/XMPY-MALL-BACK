package com.xmpy.demo.dto.req.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSearchReq {
    // 카테고리(상의/하의)
    private long categoryId;
    // 상세카테고리(후드/반팔)
    private long categoryDetailId;
    // 검색: 상품명/설명키워드
    private String keyword;
    // 가격범위
    private Integer minPrice;
    private Integer maxPrice;
    // 정렬옵션 (Sort안에있는 애들 불러옴)
    private ProductSort sort = ProductSort.latest;
    // 1페이지부터 시작
    private Integer page = 1;
    // 한페이지에 들어갈 범위 (15)
    private Integer size = 15;

}
