package com.xmpy.demo.service;


import com.xmpy.demo.dto.req.review.ReviewReqDto;
import com.xmpy.demo.dto.res.review.MyReviewResDto;
import com.xmpy.demo.dto.res.review.ReviewableItemResDto;
import com.xmpy.demo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public List<ReviewableItemResDto> getReviewableItems(String email) {
        return reviewMapper.findReviewableItemsByEmail(email);
    }

    public void writeReview(String email, ReviewReqDto dto) {
        dto.setEmail(email);
        reviewMapper.insertReview(dto);
    }

    public List<MyReviewResDto> getMyReviews(String email) {
        return reviewMapper.findMyReviewsByEmail(email);
    }
}
