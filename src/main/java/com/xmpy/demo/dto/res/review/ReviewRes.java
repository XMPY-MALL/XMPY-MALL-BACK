package com.xmpy.demo.dto.res.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class ReviewRes {
    private long reviewId;
    private String userName;   // 마스킹된 이름
    private String content;
    private LocalDateTime createdAt;
}
