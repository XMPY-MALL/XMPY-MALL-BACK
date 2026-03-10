package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProductMapper {

    // 카테고리별 상품조회 + 페이징
    // limit - 한 페이지에 몇개 가져올지
    // offset - 몇개 건너뛸지 ( page - 1 * limit)
    List<Product> findByCategoryDetailId (
            @Param("categoryDetailId") long categoryDetailId,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int countByCategoryDetailId(@Param("categoryDetailId") long categoryDetailId);

    // 상품 전체 조회
    List<Product> findAll();


    // 상품 단건 조회 (productId)
    Product findById(@Param("productId") long productId);


    // 아우터, 상의, 하의 전체 상품 조회
    List<Product> findByCategoryId(@Param("categoryId") long categoryId);


    // 후드, 맨투맨 상품 조회
    List<Product> findByDetailId(@Param("categoryDetailId") long categoryDetailId);

    // 상품 수정
    int update (Product product);

    // 상품 삭제
    int deleteById(@Param("productId") long productId);

    // 상품등록
    void insertProduct(Product product);
    // 하나의 상품에 대해 여러 썸네일 등록
    void insertThumbnails(@Param("productId") long productId, @Param("urls") List<String> urls);


}
