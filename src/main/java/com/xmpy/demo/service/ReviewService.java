package com.xmpy.demo.service;

import com.xmpy.demo.dto.res.review.ReviewListRes;
import com.xmpy.demo.dto.res.review.ReviewRes;
import com.xmpy.demo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;  // mapper 직접 주입

    public ReviewListRes getReviews(long productId, int offset, int limit) {
        List<ReviewRes> reviews = reviewMapper.findByProductId(productId, offset, limit)
                .stream()
                .map(review -> ReviewRes.builder()
                        .reviewId(review.getReviewId())
                        .userName(maskUserName(review.getUser().getUserName()))
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        long total = reviewMapper.countByProductId(productId);

        return ReviewListRes.builder()
                .reviews(reviews)
                .total(total)
                .build();
    }

    private String maskUserName(String name) {
        if (name == null || name.length() <= 1) return name;
        return name.charAt(0) + "*".repeat(name.length() - 1);
    }
}
