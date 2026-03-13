package com.xmpy.demo.dto.res.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyReviewResDto {
    private int reviewId;
    private String productName;
    private String imgUrl;
    private String content;
    private String createdAt;
}
