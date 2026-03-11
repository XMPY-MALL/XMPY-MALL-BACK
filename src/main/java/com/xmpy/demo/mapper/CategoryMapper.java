package com.xmpy.demo.mapper;

import com.xmpy.demo.entity.ProductCategory;
import com.xmpy.demo.entity.ProductCategoryDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<ProductCategory> findAllWithDetails();

    // 카테고리 디테일 전체조회 (상의 하의 하의 안 카테고리)
    List<ProductCategoryDetail> findAll();


    // 디테일 Id로 조회해서 수정

    ProductCategoryDetail findByCategoryDetailId(@Param("categoryDetailId") long categoryDetailId);
}
