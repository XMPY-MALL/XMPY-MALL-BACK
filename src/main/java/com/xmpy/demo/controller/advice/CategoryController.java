package com.xmpy.demo.controller.advice;

import com.xmpy.demo.dto.res.product.ProductCategoryMenuRes;
import com.xmpy.demo.service.CategoryMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryMenuService categoryMenuService;

    // 카테고리 메뉴(상의/하의 + 상세)
    // db결과를 카테고리 -> 상세카테고리 리스트로 변환해서 리턴
    @GetMapping("/menu")
    public List<ProductCategoryMenuRes> getCategory() {
        return categoryMenuService.getCategory();
    }
}
