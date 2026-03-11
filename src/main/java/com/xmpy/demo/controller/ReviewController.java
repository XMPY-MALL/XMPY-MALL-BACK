package com.xmpy.demo.controller;

import com.xmpy.demo.dto.res.review.ReviewListRes;
import com.xmpy.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// controller/ReviewController.java
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping // 상품상세 페이지에서 해당 상품의 리뷰들 조회 - 5개씩 페이징
    public ResponseEntity<ReviewListRes> getReviews(
            @RequestParam long productId,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit
    ) {
        return ResponseEntity.ok(reviewService.getReviews(productId, offset, limit));
    }
}