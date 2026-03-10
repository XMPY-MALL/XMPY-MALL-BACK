    package com.xmpy.demo.dto.res.product;

    import lombok.Getter;
    import lombok.Setter;
    import java.util.List;

    @Getter
    @Setter
    public class ProductCategoryMenuRes {
        private  Long productCategoryId;
        private String productCategoryName;
        private List<ProductDetailRes> details;
    }
