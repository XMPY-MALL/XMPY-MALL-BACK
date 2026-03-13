package com.xmpy.demo.service;

import com.xmpy.demo.dto.req.auth.SignInReqDto;
import com.xmpy.demo.dto.req.auth.SignupReqDto;
import com.xmpy.demo.entity.User;
import com.xmpy.demo.exeption.UserException;
import com.xmpy.demo.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.xmpy.demo.mapper.UserMapper;

import java.util.Map;


// 회원가입 -> DB 저장 -- 메서드..
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;


    // User객체만 가져오면, 토큰 쌍으로 바꿔서 리턴
    private String generateToken(User user){
        // 토큰에 담을 sub, extraClaims를 User로부터 추출
        String sub = user.getEmail();

        Map<String, Object> extraClaims = Map.of(
                "role", user.getRoleId(),
                "userId", user.getUserId() // userId를 jwt에 추가 - order, review등에서 사용할것
                // 강사님은 user.getRole().getRoleName()으로 작성
                // 강사님의 entity와 나의 entity부분이 다른데, 어떤 방식으로 해줘야 하나..
        );

        String accessToken = jwtUtil.generateAccessToken(sub, extraClaims);

        return accessToken;
    }

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

    // 2. 로그인
    public String signIn(SignInReqDto dto){
        // 아이디 확인
        // 실제 아이디(이메일)가 있는지 검사
        User user = userMapper.getUserByEmail(dto.getEmail())
                .orElseThrow(() -> new UserException("이메일 또는 비밀번호를 잘못 입력하셨습니다 다시 입력해주세요",
                        HttpStatus.BAD_REQUEST));

        // 비밀번호 확인
        // encoder.matches(평문암호, 암호화된 암호)
        // dto에 니가 입력한 값과, 실제 mysql에 있는 값을 비교한다
        // 지금 계속 password에서 문제가 발생하고 있음....
        if (!encoder.matches(dto.getPassword(), user.getUserPassword())){
            // 비밀번호 틀렸을때
            throw new UserException("이메일 또는 비밀번호 잘못 입력하셨습니다 다시 확인해주세요"
                    , HttpStatus.BAD_REQUEST);
        }

        return generateToken(user);
    }
}
