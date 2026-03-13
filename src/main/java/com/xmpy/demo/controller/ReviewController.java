package com.xmpy.demo.controller;

import com.xmpy.demo.dto.res.review.ReviewListRes;
import com.xmpy.demo.dto.req.review.ReviewReqDto;
import com.xmpy.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    // 1. 리뷰를 작성할 수 있는 상품목록 가져오기
    @GetMapping("/me/items")
    public ResponseEntity<?> getReviewableItems(Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(reviewService.getReviewableItems(email));
    }

    // 2. 리뷰를 등록하도록 하는 API
    @PostMapping
    public ResponseEntity<?> writeReview(Authentication authentication,
                                         @RequestBody ReviewReqDto dto) {
        String email = authentication.getName();
        reviewService.writeReview(email, dto);
        return ResponseEntity.ok("리뷰가 작성되었습니다");
    }

    // 3. 내가 작성한 리뷰목록 조회하기
    @GetMapping("/me")
    public ResponseEntity<?> getMyReviews(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(reviewService.getMyReviews(email));
    }
}
