package repository.mapper;


import entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductCategoryMapper {

    // 상품 카테고리별 상품조회 (후드,맨투맨같은 디테일 카테고리)



    // 상품 단건 등록
    int insert(ProductCategory productCategory);

    // 상품 단건 수정
    int update(ProductCategory productCategory);

    // 상품 단건 삭제(id)
    int deleteProductById(long id);
}
