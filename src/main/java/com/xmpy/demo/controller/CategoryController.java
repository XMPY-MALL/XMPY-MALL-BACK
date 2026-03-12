package com.xmpy.demo.controller;

import com.xmpy.demo.dto.res.category.CategoryRes;
import com.xmpy.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryRes>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
