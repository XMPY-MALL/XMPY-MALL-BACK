package com.xmpy.demo.dto.res.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ReviewListRes {
    private List<ReviewRes> reviews;
    private long total;
}
