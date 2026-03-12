package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.auth.SignInReqDto;
import com.xmpy.demo.dto.req.auth.SignupReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xmpy.demo.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    // 일단 로그인 / 로그 아웃 / 회원가입 구현 - 따로 UserController만들어야 하는가?
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @RequestBody @Valid SignupReqDto dto){

        authService.signUp(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("계정생성 완료");
    }

    // 로그인
    // 논리적으로는 getMapping이 맞지만, -> 하지만, param등 민감정보가 노출
    // 민감한 정보를 주고 받아야 한다 -> body가 필요함.. -> post
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInReqDto reqDto){
        return ResponseEntity.ok(authService.signIn(reqDto));
    }
}
