package com.xmpy.demo.dto.req.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInReqDto {
    private String email;
    // email == 아이디 이고, unique 걸어줌

    private String password;
    // password는 굳이 db에서 unique걸어줄 필요없다
}
