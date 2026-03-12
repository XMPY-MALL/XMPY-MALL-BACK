package com.xmpy.demo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    private long stockId;
    private long productId;
    private long sizeId;
    private String sizeName;
    private long colorId;
    private String colorName;
    private int count;
    private boolean isSoldOut;
    private LocalDateTime updatedAt;

    private Color color;  // 그래프 탐색용
    private Size size;    // 그래프 탐색용

    // 롬복 boolean 타입 setter는 명명이 다름
    public void setIsSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
