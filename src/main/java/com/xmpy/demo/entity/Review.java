package com.xmpy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private long reviewId;
    private long productId;
    private long orderItemId;
    private long userId;
    private String content;
    private LocalDateTime createdAt;

    private String adminReply;
    private LocalDateTime adminReplyCreatedAt;
    private LocalDateTime adminReplyUpdatedAt;

    private User user;
}
