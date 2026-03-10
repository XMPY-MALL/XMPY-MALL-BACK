package com.xmpy.demo.mapper;


import com.xmpy.demo.dto.res.product.ProductCategoryMenuRowRes;
import com.xmpy.demo.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCategoryMapper {



    // ✅ 카테고리 메뉴용 조회 (상의/하의 + 디테일)
    List<ProductCategoryMenuRowRes> findMenuRows();

    // 상품 카테고리별 상품조회 (후드,맨투맨같은 디테일 카테고리)



    // 상품 단건 등록
    int insert(ProductCategory productCategory);

    // 상품 단건 수정
    int update(ProductCategory productCategory);

    // 상품 단건 삭제(id)
    int deleteById(long id);
}
