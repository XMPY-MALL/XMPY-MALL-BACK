package com.xmpy.demo.dto.res.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRespDto {
    private long stockId;
    private long productId;
    private long sizeId;
    private String sizeName;
    private long colorId;
    private String colorName;
    private int count;
    private int isSoldOut;
    private String updatedAt;
}
