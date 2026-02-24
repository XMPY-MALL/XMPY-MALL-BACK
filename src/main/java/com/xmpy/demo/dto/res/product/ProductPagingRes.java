package com.xmpy.demo.dto.res.product;

import com.xmpy.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPagingRes {
    private int page; // 현재페이지
    private int totalPages; // 전체 페이지 수
    private int totalItemCount; // 전체 상품 갯수
    private List<Product> items; // 현재 페이지 상품 리스트
}
