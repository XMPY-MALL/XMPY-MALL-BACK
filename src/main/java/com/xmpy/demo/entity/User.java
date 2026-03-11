package com.xmpy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private long userId;
    private String email;
    private String userPassword;
    private String userName;
    private long userPhone;
    // 기능 문제 때문에 userPhone을 String으로 수정함
    private String address;
    private LocalDateTime createAt;

    private long roleId;
}
