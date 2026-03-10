    package com.xmpy.demo.mapper;

    import com.xmpy.demo.dto.req.product.ProductSearchReq;
    import com.xmpy.demo.dto.res.product.ProductCategoryMenuRowRes;
    import com.xmpy.demo.dto.res.product.ProductListRowRes;
    import com.xmpy.demo.entity.Product;
    import com.xmpy.demo.entity.ProductThumbnail;
    import org.apache.ibatis.annotations.Mapper;
    import org.apache.ibatis.annotations.Param;
    import java.util.List;

    @Mapper
    public interface ProductMapper {

        List<ProductListRowRes>search(@Param("req")ProductSearchReq req);

        // 카테고리별 상품조회 + 페이징
        List<Product> findByCategoryDetailId(
                @Param("categoryDetailId") long categoryDetailId,
                @Param("limit") int limit,
                @Param("offset") int offset
        );

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



        int updateBest(@Param("productId") long productId, @Param("best") boolean best);

        List<Product> bestList();


    }