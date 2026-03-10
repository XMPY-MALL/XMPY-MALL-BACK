package com.xmpy.demo.dto.req.product;

// 상품 리스트 (검색/필터/정렬/페이징)
// 파라미터를 안전하게 받기위해서 사용

public enum ProductSort {
    latest, // 최신순
    best, // 베스트
    priceAsc, // 가격 낮은순
    priceDesc // 가격 높은순
}
