package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.review.ReviewReqDto;
import com.xmpy.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

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
