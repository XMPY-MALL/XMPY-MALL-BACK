package repository.mapper;

import entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    // 상품 전체 조회
    List<Product> findAll();


    // 상품 단건 조회 (productId)
    Product findById(@Param("productId") long productId);


    // 아우터, 상의, 하의 전체 상품 조회
    List<Product> findByCategoryId(@Param("categoryId") long categoryId);


    // 후드, 맨투맨 상품 조회
    List<Product> findByDetailId(@Param("categoryDetailId") long categoryDetailId);


    // 상품 등록
    int insert (Product product);

    // 상품 수정
    int update (Product product);

    // 상품 삭제
    int deleteById(@Param("productId") long productId);


}
