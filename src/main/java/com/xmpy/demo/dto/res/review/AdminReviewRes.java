package com.xmpy.demo.dto.res.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminReviewRes {
    private long reviewId;
    private String userName;
    private String productName;
    private Long orderId;
    private String content;
    private LocalDateTime createdAt;
    private String adminReply;
    private LocalDateTime adminReplyCreatedAt;
    private LocalDateTime adminReplyUpdatedAt;
}