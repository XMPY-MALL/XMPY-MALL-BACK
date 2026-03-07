package com.xmpy.demo.dto.req.stock;

import lombok.Data;

@Data
public class StockAddReqDto {
    private long sizeId;
    private long colorId;
    private int count;
}
