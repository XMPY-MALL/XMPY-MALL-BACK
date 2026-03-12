package com.xmpy.demo.service;

import com.xmpy.demo.dto.res.category.CategoryRes;
import com.xmpy.demo.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<CategoryRes> getAll() {
        return categoryMapper.findAllWithDetails().stream()
                .map(CategoryRes::from)
                .toList();
    }
}