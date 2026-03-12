package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.user.UpdateUserReqDto;
import com.xmpy.demo.dto.res.user.UserResDto;
import com.xmpy.demo.entity.User;
import com.xmpy.demo.exeption.UserException;
import com.xmpy.demo.jwt.JwtUtil;
import com.xmpy.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    // 강사님은 userId를 아이디로 사용하였고,
    // 우리는 email을 아이디로 사용하였기 때문에
    // 우리는 email을 받아와야 한다
    public UserResDto getUserInfo(String email){
        User user = userMapper.getUserByEmail(email)
                .orElseThrow(() -> new UserException(
                        "사용자를 찾을 수 없습니다",
                        HttpStatus.NOT_FOUND
                ));

        return UserResDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .userPhone(user.getUserPhone())
                .address(user.getAddress())
                .roleId(user.getRoleId())
                .build();
    }


    @Transactional
    public void updateUserInfo(String email, UpdateUserReqDto reqDto){

        // 1. 유저 조회
        User user = userMapper.getUserByEmail(email)
                .orElseThrow(() -> new UserException(
                        "사용자를 찾을 수 없습니다",
                        HttpStatus.NOT_FOUND
                ));

        // 2. 현재 비밀번호 검증
        if (!passwordEncoder.matches(reqDto.getUserPassword(), user.getUserPassword())){
            // 내가 입력한 password창의 password를 들고온다 , 실제 password를 들고온다
            throw new UserException(
                    "현재 비밀번호가 일치하지 않습니다",
                    HttpStatus.BAD_REQUEST
            );
        }

        // 3. 새로운 비밀 번호 암호화
        String encodePassword = passwordEncoder.encode(reqDto.getNewPassword());

        // 4. DB 업데이트
        // email전달하고, 인코딩된 password전달하고, address전달하고, 핸드폰 이름 전달한다
        userMapper.updateUserInfo(email, encodePassword, reqDto.getAddress(), reqDto.getUserPhone());
    }
}