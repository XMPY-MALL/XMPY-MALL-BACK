package com.xmpy.demo.dto.res.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SigninResDto {
    private String accessToken;
    private String refreshToken; // refresh토큰 관련 코드
}
