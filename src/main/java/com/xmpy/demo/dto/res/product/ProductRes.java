package com.xmpy.demo.dto.res.product;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ProductRes {
    private Long productId;
    private String productName;
    private String description;
    private boolean best;
    private List<String> imgUrls;
    private int price;
    private String quickRundown;
    private String productDetailContent;
}
