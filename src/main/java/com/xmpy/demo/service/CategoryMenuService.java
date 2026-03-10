package com.xmpy.demo.service;


import com.xmpy.demo.dto.res.product.ProductCategoryMenu;
import com.xmpy.demo.dto.res.product.ProductCategoryMenuRes;
import com.xmpy.demo.dto.res.product.ProductCategoryMenuRowRes;
import com.xmpy.demo.dto.res.product.ProductDetailRes;
import com.xmpy.demo.mapper.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryMenuService {

    private final ProductCategoryMapper productCategoryMapper;

    public List<ProductCategoryMenuRes> getCategory() {

        // 상의 - 반팔
        // 하의 - 청바지
        List<ProductCategoryMenuRowRes> rows = productCategoryMapper.findMenuRows();
        List<ProductCategoryMenuRes> result = new ArrayList<>();

        //DB에서 가져온 row 하나씩 반복
        for (ProductCategoryMenuRowRes row : rows) {
            // result에 카테고리가 있는지 없는지 확인이 필요
            ProductCategoryMenuRes findCategory = null;


            for (ProductCategoryMenuRes category : result) {
                if (category.getProductCategoryId()
                        .equals(row.getProductCategoryId())) {

                    // 존재하면 저장
                    findCategory = category;
                    break;
                }
            }

            // 카테고리가 없으면 새로만들기!
            if (findCategory == null) {

                ProductCategoryMenuRes newCategory = new ProductCategoryMenuRes();
                newCategory.setProductCategoryId(row.getProductCategoryId());
                newCategory.setProductCategoryName(row.getProductCategoryName());

                // 상세카테고리 리스트 초기화
                newCategory.setDetails(new ArrayList<>());

                result.add(newCategory);
                findCategory = newCategory;
            }

            // 5️⃣ 상세 카테고리 객체 생성 (반팔, 긴팔 등)
            ProductDetailRes detail = new ProductDetailRes();
            detail.setProductCategoryDetailId(row.getProductCategoryDetailId());
            detail.setProductCategoryDetailName(row.getProductCategoryDetailName());

            // 6️⃣ 해당 카테고리의 details 리스트에 추가
            findCategory.getDetails().add(detail);


        }
        return result;

    }
}
