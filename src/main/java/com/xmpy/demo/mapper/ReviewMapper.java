package com.xmpy.demo.mapper;

import com.xmpy.demo.dto.res.review.AdminReviewRes;
import com.xmpy.demo.dto.res.review.ReviewRes;
import com.xmpy.demo.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findByProductId(
            @Param("productId") long productId,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    long countByProductId(@Param("productId") long productId);

    List<AdminReviewRes> findAdminReviews();

    int updateAdminReply(@Param("reviewId") long reviewId,
                         @Param("adminReply") String adminReply);
}