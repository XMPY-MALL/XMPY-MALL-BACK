package com.xmpy.demo.dto.req.user;

import lombok.Builder;
import lombok.Data;

// 유저 정보 수정 요청
@Data
@Builder
public class UpdateUserReqDto {
    private String userPassword; // 현재 비밀번호
    private String newPassword; // 새 비밀번호
    private String address; // 새 주소
    private String userPhone; // 전화번호
}