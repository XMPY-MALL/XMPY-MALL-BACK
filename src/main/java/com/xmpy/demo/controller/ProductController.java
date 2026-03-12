package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.product.ProductAddReq;
import com.xmpy.demo.dto.res.product.SubMenuResDto;
import com.xmpy.demo.dto.req.product.ProductSearchReq;
import com.xmpy.demo.dto.res.product.ProductListRowRes;
import com.xmpy.demo.dto.res.product.ProductRes;
import com.xmpy.demo.entity.Product;
import com.xmpy.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 전체 상품 조회
    @GetMapping
    public List<Product> getAll() {
        System.out.println("gogogogo");
        return productService.getAll();
    }

    // 단건조회
    @GetMapping("/{productId}")
    public ProductRes getById(@PathVariable long productId) {
        return productService.getProductDetail(productId);
    }


    // 카테고리(상의/ 하의)로 조회
    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable long categoryId) {
        return productService.getByCategoryId(categoryId);
    }

    // 디테일(맨투맨 / 반팔 / 긴바지) 로 조회 - 페이지네이션 추가
    @GetMapping("/detail/{categoryDetailId}")
    public ResponseEntity<SubMenuResDto> getByDetailId(
            @PathVariable long categoryDetailId,
            @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(productService.getCategoryDetailPaging(categoryDetailId, page));
    }

    // 상품검색/필터/정렬 (모든유저사용가능)
    // product/search?keyword=후드&sort=best
    // 후드라는 이름이 들어간 상품만 조회
    @GetMapping("/search")
    public List<ProductListRowRes> search(ProductSearchReq req) {
        // @RequestParam을 DTO로 한번에
        return productService.search(req);
    }

    // 상품 추가
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductAddReq req) {
        productService.addProduct(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품 추가 완료");
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public int update(@PathVariable long productId, @RequestBody Product product) {
        product.setProductId(productId);
        return productService.update(product);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public int delete(@PathVariable long productId) {
        return productService.delete(productId);
    }

    // 베스트 여부 변경 (admin)
    // patch/product/1/best?best=true

    @PatchMapping("/{productId}/best")
    public int setBest(@PathVariable long productId, @RequestParam boolean best) {
        return productService.setBest(productId, best);

    }

    // 모든유저가 get으로 볼수있음
    @GetMapping("/product/best")
    public ResponseEntity<?> bestList() {
        return ResponseEntity.ok(productService.bestList());
    }

}