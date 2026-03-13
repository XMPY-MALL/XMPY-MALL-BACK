package com.xmpy.demo.controller;

import com.xmpy.demo.dto.req.user.UpdateUserReqDto;
import com.xmpy.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// 따로 RequestMapping은 설정안하는게 맞을 듯
// 왜냐하면, 3개다 서로다른 종류의 데이터를 들고 오기 때문
// 1. 주문내역은 /order/list 같은 형식으로
// 2. 리뷰작성은 /review/list 같은 형식으로
// --> 아니다 그냥 그 "/mypage" 를 공통으로 주소를 가지도록 하자
@RequestMapping("/user")
public class MyPageController {
    // MyPageController에서는
    // "주문내역, 리뷰작성, 개인정보 수정" 에 해당하는 데이터들을 쫙 들고 올것이다
    private final UserService userService;

    // 현재 로그인한 유저의 email 가져오기
    private String getCurrentUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: " + authentication);
        String email = (String) authentication.getPrincipal();
        System.out.println("email: " + email);
        return authentication.getName();
    }

    // 0. 유저 정보 조회
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(){
        String email = getCurrentUserEmail();
        return ResponseEntity.ok(userService.getUserInfo(email));
    }

    // 1. 유저 정보 수정
    @PutMapping("/me")
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserReqDto reqDto){
        String email = getCurrentUserEmail();
        userService.updateUserInfo(email, reqDto);
        return ResponseEntity.ok().build();
    }
}