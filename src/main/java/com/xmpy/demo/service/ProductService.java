package com.xmpy.demo.service;


import com.xmpy.demo.dto.req.product.ProductSearchReq;
import com.xmpy.demo.dto.res.product.*;
import com.xmpy.demo.entity.Product;
import com.xmpy.demo.entity.ProductThumbnail;
import com.xmpy.demo.exeption.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xmpy.demo.mapper.ProductMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public int insert(Product product) {

        // createAt/updateAt을 넣어서
        // db에 null 들어가는 문제, 프론트 누락 문제 막기
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        return productMapper.insert(product);
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
            item.setThumbnailUrl(p.getProductThumbnails().get(0).getProductUrl());
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


}
