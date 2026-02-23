    package service;


    import dto.res.product.ProductPagingRes;
    import entity.Product;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import repository.mapper.ProductMapper;

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



    }
