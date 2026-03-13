package com.xmpy.demo.dto.req.review;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReviewReqDto {
    private int orderItemId;
    private String content;
    private String email;
}
