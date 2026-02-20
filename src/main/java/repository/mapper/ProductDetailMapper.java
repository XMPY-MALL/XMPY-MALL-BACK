package repository.mapper;


import entity.ProductDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDetailMapper {

    // 상품상세 단건 조회
    ProductDetail findById(@Param("productDetailId") long productDetailId);


    // 상세 상품 등록
    int insert(ProductDetail productDetail);

    // 상세 상품 수정
    int update(ProductDetail productDetail);

    // 상세 상품 삭제
    int deleteById(@Param("productDetailId") long productDetailId);

}
