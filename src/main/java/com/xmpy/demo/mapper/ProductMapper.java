    package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.Product;
import com.xmpy.demo.view.ProductSubMenu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

        List<ProductListRowRes>search(@Param("req")ProductSearchReq req);

    // 카테고리별 상품조회 + 페이징
    // limit - 한 페이지에 몇개 가져올지
    // offset - 몇개 건너뛸지 ( page - 1 * limit)

        int countByCategoryDetailId(@Param("categoryDetailId") long categoryDetailId);

        // 상품 전체 조회
        List<Product> findAll();


        // 단건 조회 (썸네일 포함)
        Product findById(long productId);

        // 카테고리(아우터/상의/하의) 조회
        List<Product> findByCategoryId(@Param("categoryId") long categoryId);

        // 디테일(후드/맨투맨 등) 조회
        List<Product> findByDetailId(@Param("categoryDetailId") long categoryDetailId);

        // 상품 등록/수정/삭제
        int insert(Product product);
        int update(Product product);
        int deleteById(@Param("productId") long productId);


    // 상품 수정
    int update (Product product);


    // 상품등록
    void insertProduct(Product product);
    // 하나의 상품에 대해 여러 썸네일 등록
    void insertThumbnails(@Param("productId") long productId, @Param("urls") List<String> urls);


    // 변경 - Product → ProductSubMenu
    List<ProductSubMenu> findByCategoryDetailId(
            @Param("categoryDetailId") long categoryDetailId,
            @Param("size") int size,
            @Param("offset") int offset
    );

    // 추가
    @MapKey("product_id") // Map으로 리뷰갯수 가져올거임 또 엔티티만들기 시름
    Map<Long, Map<String, Object>> findReviewCountByProductIds(@Param("productIds") List<Long> productIds);

}
