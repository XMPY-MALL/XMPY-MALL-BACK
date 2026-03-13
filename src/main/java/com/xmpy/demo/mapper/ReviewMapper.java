package com.xmpy.demo.mapper;

import com.xmpy.demo.dto.res.review.AdminReviewRes;
import com.xmpy.demo.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.xmpy.demo.dto.req.review.ReviewReqDto;
import com.xmpy.demo.dto.res.review.MyReviewResDto;
import com.xmpy.demo.dto.res.review.ReviewableItemResDto;


@Mapper
public interface ReviewMapper {
    List<Review> findByProductId(
            @Param("productId") long productId,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    long countByProductId(@Param("productId") long productId);

    List<AdminReviewRes> findAdminReviews();
    // 우리는 아이디가 이메일이기 때문에, 이메일을 통해서, 해당 유저를
    // 찾고, 그 유저의 리뷰를 조회하고 수정도 한다

    // 1. 메일로 유저 찾기
    List<ReviewableItemResDto> findReviewableItemsByEmail(String email);

    // 2. 유저에 리뷰 달기
    void insertReview(ReviewReqDto dto);

    List<MyReviewResDto> findMyReviewsByEmail(String email);


    int updateAdminReply(@Param("reviewId") long reviewId,
                         @Param("adminReply") String adminReply);
}