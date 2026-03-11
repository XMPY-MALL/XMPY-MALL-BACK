package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.product.ProductAddReq;
import com.xmpy.demo.dto.res.product.SubMenuResDto;
import com.xmpy.demo.entity.Product;
import com.xmpy.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 전체 상품 조회
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // 상품 단건 조회 (Id로)
    @GetMapping("/{productId}")
    public Product getById(@PathVariable long productId) {
        return productService.getById(productId);
    }

    // 카테고리(상의/ 하의)로 조회
    @GetMapping("/category/{categoryId}")
    public List<Product> getBycategoryId(@PathVariable long categoryId) {
        return productService.getByCategoryId(categoryId);
    }

    // 디테일(맨투맨 / 반팔 / 긴바지) 로 조회 - 페이지네이션 추가
    @GetMapping("/detail/{categoryDetailId}")
    public ResponseEntity<SubMenuResDto> getByDetailId(
            @PathVariable long categoryDetailId,
            @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(productService.getCategoryDetailPaging(categoryDetailId, page));
    }

    // 상품 추가
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductAddReq req) {
        productService.addProduct(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품 추가 완료");
    }

    // 상품 수정
    @PutMapping
    public int update(@RequestBody Product product) {
        return productService.update(product);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public int delete(@PathVariable long productId) {
        return productService.delete(productId);
    }

}