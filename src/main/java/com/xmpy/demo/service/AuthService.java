package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.auth.SignupReqDto;
import com.xmpy.demo.entity.User;
import com.xmpy.demo.exeption.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.xmpy.demo.mapper.UserMapper;


// 회원가입 -> DB 저장 -- 메서드..
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    public void signUp(SignupReqDto dto) {

        // 1. 아이디, 이메일 중복검사..
        boolean isDuplicatedEmail = userMapper
                .getUserByEmail(dto.getEmail())
                .isPresent(); // Optional안에 값이 있으면, true

        if (isDuplicatedEmail) {
            throw new UserException("이미 존재하는 이메일 입니다.", HttpStatus.CONFLICT);
        }

        // 이거 넘으면, 1단계 통과

        // 2. SignupReqDto(dto) -> entity 로 바꿔준다...
        User user = dto.toEntity();
        // password를 암호화해서 set해줘야 한다
        // 원래는 정상적인 암호지만, 이걸 거치면 변태같은 암호가 된다
        // 1234 --> !@$!@2141241~~
        user.setUserPassword(encoder.encode(dto.getUserPassword()));


        // 3. db에 저장 (password만 바꾼채로 db에 저장)
        int successCount = userMapper.addUser(user);
        // MyBatis는 Insert(addUser)실행이후 한개의 행이 추가되면,
        // 자동으로 1값을 반한해준다

        // 실패하면, 당연히 값은 0 => 따라서 UserException실행
        if (successCount <= 0) {
            throw new UserException(
                    "회원가입 중 에러가 발생하였습니다",
                    HttpStatus.INTERNAL_SERVER_ERROR  // 500
            );
        }
    }
}
