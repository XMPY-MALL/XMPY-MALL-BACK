package com.xmpy.demo.service;
import com.xmpy.demo.dto.req.product.ProductAddReq;
import com.xmpy.demo.dto.res.product.ProductPagingRes;
import com.xmpy.demo.dto.res.product.SubMenuResDto;
import com.xmpy.demo.entity.Color;
import com.xmpy.demo.entity.Product;
import com.xmpy.demo.entity.ProductDetail;
import com.xmpy.demo.entity.Size;
import com.xmpy.demo.mapper.*;
import com.xmpy.demo.view.ProductSubMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService  {

    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;
    private final SizeMapper sizeMapper;
    private final ColorMapper colorMapper;
    private final StockMapper stockMapper;

    public SubMenuResDto getCategoryDetailPaging(long categoryDetailId, int page) {
        if (page < 1) page = 1;

        int size = 15;
        int totalCount = productMapper.countByCategoryDetailId(categoryDetailId);
        int totalPages = Math.max(1, (totalCount + size - 1) / size);
        int offset = (page - 1) * size;

        List<ProductSubMenu> products = productMapper.findByCategoryDetailId(categoryDetailId, size, offset);

        List<Long> productIds = products.stream()
                .map(ProductSubMenu::getProductId)
                .toList();

        Map<Long, Map<String, Object>> reviewCountMap = productIds.isEmpty()
                ? Map.of()
                : productMapper.findReviewCountByProductIds(productIds);

        List<SubMenuResDto.SubMenuProductResDto> productDtos = products.stream()
                .map(p -> {
                    int reviewCount = reviewCountMap.containsKey(p.getProductId())
                            ? ((Number) reviewCountMap.get(p.getProductId()).get("review_count")).intValue()
                            : 0;
                    return SubMenuResDto.SubMenuProductResDto.from(p, reviewCount);
                })
                .toList();

        String categoryDetailName = products.isEmpty() ? "" : products.get(0).getCategoryDetailName();

        return SubMenuResDto.builder()
                .categoryDetailName(categoryDetailName)
                .totalPages(totalPages)
                .products(productDtos)
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
    public Product getById(long productId) {
        Product p = productMapper.findById(productId);
        if (p == null) {
            throw new ProductException("해당 아이디의 상품은 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        }
        return p;
    }

    public ProductRes getProductDetail(long productId) {
        Product p = this.getById(productId);

        List<String> imgUrls = p.getProductThumbnails().stream()
                .map(ProductThumbnail::getProductUrl)
                .collect(Collectors.toList());

        return ProductRes.builder()
                .productId(p.getProductId())
                .productName(p.getProductName())
                .description(p.getDescription())
                .best(p.isBest())
                .price(p.getPrice())
                .quickRundown(p.getQuickRundown())
                .imgUrls(imgUrls)
                .build();
    }

    public List<ProductListRowRes> search(ProductSearchReq req) {
        // req가 null로 들어오는걸 방지
        if(req == null) {
            req = new ProductSearchReq();
        }
        return productMapper.search(req);
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

    public int setBest(long productId, boolean best) {
        return productMapper.updateBest(productId, best);
    }



    public List<ProductBestCategoryRes> bestList() {

        List<Product> bestProducts = productMapper.bestList();

        Map<Long, List<ProductBestItemRes>> categoryMap = new LinkedHashMap<>();

        for (Product p : bestProducts) {

            Long categoryId = p.getCategoryId();

            ProductBestItemRes item = new ProductBestItemRes();
            item.setProductId(p.getProductId());
            item.setProductName(p.getProductName());
            item.setPrice(p.getPrice());
            List<ProductThumbnail> thumbnails = p.getProductThumbnails();
            if (thumbnails != null && !thumbnails.isEmpty()) {
                item.setThumbnailUrl(thumbnails.get(0).getProductUrl());
            }
            item.setBest(p.isBest());
            item.setSoldOut(false);
            item.setReviewCount(0);

            categoryMap.computeIfAbsent(categoryId, k -> new ArrayList<>()).add(item);
        }

        List<ProductBestCategoryRes> result = new ArrayList<>();

        for (Long key : categoryMap.keySet()) {

            ProductBestCategoryRes res = new ProductBestCategoryRes();

            if (key == 1) {
                res.setCategoryName("상의");
            } else if (key == 2) {
                res.setCategoryName("하의");
            } else if (key == 3) {
                res.setCategoryName("아우터");
            }

            res.setProducts(categoryMap.get(key));
            result.add(res);
        }

        return result;
    }

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
