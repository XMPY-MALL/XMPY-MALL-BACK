package com.xmpy.demo.controller;

import com.xmpy.demo.entity.Product;
import com.xmpy.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


}
