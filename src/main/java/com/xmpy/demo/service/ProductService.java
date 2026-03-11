package com.xmpy.demo.service;
import com.xmpy.demo.dto.req.product.ProductAddReq;
import com.xmpy.demo.dto.res.product.ProductPagingRes;
import com.xmpy.demo.entity.Color;
import com.xmpy.demo.entity.Product;
import com.xmpy.demo.entity.ProductDetail;
import com.xmpy.demo.entity.Size;
import com.xmpy.demo.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  {

    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;
    private final SizeMapper sizeMapper;
    private final ColorMapper colorMapper;
    private final StockMapper stockMapper;

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
    public void insert(Product product) {
         productMapper.insertProduct(product);
    }


    // 상품 수정
    public int update(Product product) {
        product.setUpdatedAt(LocalDateTime.now());
        return productMapper.update(product);
    }

    // 상품 삭제
    public int delete(long productId) {

        return productMapper.deleteById(productId);
    }

    // 다건 상품 삭제

    @Transactional
    public void addProduct(ProductAddReq req) {
        // 1. product INSERT
        Product product = Product.builder()
                .productName(req.getProductName())
                .description(req.getDescription())
                .price(req.getPrice())
                .categoryId(req.getSelectedCategoryId())
                .categoryDetailId(req.getSelectedSubCategoryId())
                .build();
        productMapper.insertProduct(product);

        // 2. thumbnail INSERT
        productMapper.insertThumbnails(product.getProductId(), req.getImageUrls());

        // 3. detail INSERT
        ProductDetail detail = ProductDetail.builder()
                .productId(product.getProductId())
                .productDetailContent(req.getDetailContent())
                .build();
        productDetailMapper.insert(detail);

        // 4. stock INSERT
        for (ProductAddReq.StockReq stockReq : req.getStocks()) {
            long sizeId = getOrCreateSizeId(stockReq.getSize());
            long colorId = getOrCreateColorId(stockReq.getColor());
            stockMapper.insertStock(
                    product.getProductId(),
                    sizeId,
                    colorId,
                    stockReq.getQuantity()
            );
        }
    }

    private long getOrCreateSizeId(String sizeName) {
        Long sizeId = sizeMapper.findIdByName(sizeName);
        if (sizeId == null) {
            Size size = Size.builder().sizeName(sizeName).build();
            sizeMapper.insertSize(size);
            sizeId = size.getSizeId();
        }
        return sizeId;
    }

    private long getOrCreateColorId(String colorName) {
        Long colorId = colorMapper.findIdByName(colorName);
        if (colorId == null) {
            Color color = Color.builder().colorName(colorName).build();
            colorMapper.insertColor(color);
            colorId = color.getColorId();
        }
        return colorId;
    }


}
