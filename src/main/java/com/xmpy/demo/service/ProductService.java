package service;


import dto.res.product.ProductPagingRes;
import entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.mapper.ProductMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {

    private final ProductMapper productMapper;

    public ProductPagingRes getCategoryDetailPaging(
            long categoryDetailId, int page) {

        if (page < 1) {
            page = 1;
        }

        int size = 15; // 한 페이지당 15개씩 나오게

        // 총 갯수
        int totalCount = productMapper.countByCategoryDetailId(categoryDetailId);

        // 총 페이지 수
        int totalPages = Math.max(1, (totalCount + size - 1) / size);

        // offset 계산
        int offset = (page -1) * size;

        List<Product> list = productMapper.findByCategoryDetailId(categoryDetailId, size, offset);

        return ProductPagingRes.builder()
                .page(page)
                .totalItemCount(totalCount)
                .totalPages(totalPages)
                .items(list)
                .build();

    }

    // 전체 상품 조회
    public List<Product> getAll() {
        return productMapper.findAll();
    }

    // 상품 상의/ 하의 / 아우터 이런식으로 조회
    public List<Product> getByCategoryId(long categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }

    // 상품 디테일로 조회
    public List<Product> getByDetailId(long categoryDetailId) {
        return productMapper.findByDetailId(categoryDetailId);
    }

    // 단건조회
    public Product getById(long productId){
        return productMapper.findById(productId);
    }

    // 상품 등록


    // 상품 수정
    public int update(Product product) {
        product.setUpdatedAt(LocalDateTime.now());
        return productMapper.update(product);
    }

    // 상품 삭제
    public int delete(long productId) {
        return productMapper.deleteById(productId);
    }



}
