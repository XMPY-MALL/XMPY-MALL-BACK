package com.xmpy.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

//  "로그인 / 로그아웃" 구현 시작 --> JwtUtil부터 로그인/로그아웃 내부백엔드 구현 시작
//  토큰 발급및 토큰검증 관련 코드 작성하기 - JwtUtil부터가 시작임..
@Component
public class JwtUtil { // ( jwt 토큰 발급 + jwt 토큰 검증 )
    private final SecretKey key;
    private final long accessExpireMillis;

    public JwtUtil(
            // @Value로 yaml에 있는 secret, expire-millis, refresh~ 다 들고오기
            /*
            jwt:
              secret: "6Y9tMfkTPJPDQCCNJDRCaohhhCSQA3wONNmeC7pN9lISm3H4KBpid9HyF5opwPxjQbXgmzj4V0h3cH0X0ldrMg=="
              access-expire-millis: 900000 # 15분 -> 밀리세컨드
              refresh-expire-millis: 86400000 # 1일 (1 * 24 * 60 * 60 * 1000)
            -- application.yaml에 있는 정보들을 다 들고온다.. -- @Value
            */
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expire-millis}") long accessExpireMillis
    ){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        // 암호(secret)을 Base64로 인코딩(변환)을 하면, 그 안전한 문자열로 변환이 가능하다..
        // 단순하게 형식만 변화시키는 것...

        this.accessExpireMillis = accessExpireMillis;
        // 토큰의 유효기간 : jwt자체에 토큰의 만료시간이 존재 ( 만료시간이 박혀있기 때문에, 그 시간이 지나면, 토큰이 자동으로 무효 )
        // 로그인을 해서 (AccessToken을 발급받으면, 해당 시간동안, api요청 가능 ( 메인페이지 접근, 상품목록 보기, 장바구니 보기, 주문, 제품상세보시)

    }




    // jwt 토큰 -> 암호화된 문자열
    // jwt토큰의 구조: [head], [payload], [signature]
    /*
        jwt토큰 -> 암호화된 문자열
        "jwt토큰의 구조" -> "[head], [payload], [signature]"
        head: 토큰의 메타데이터
        payload: key-value 형태로 데이터 저장 할 수 있음 (유저 식별, 생성시간, 만료시간)
        signature: 위조여부를 검사하는 검사지 - head나 payload를 조작하면 발생한다
    */
    // 토큰 발급
    private String buildToken(
            // buildToken(subject, accessExpireMillis, claims, "ACCESS"); 이런식으로 써줌...
            // 이렇게 해서 builder이 payload형태로 만들어지고, 그 payload와 key(secret)을 조합해서 토큰을 만들어낸다.

            String subject, // 유저 식별
            long expireMillis, // 만료시간
            Map<String, Object> extraClaims // payload에 담을 데이터
    ){
        long now = System.currentTimeMillis(); // 현재 시간
        long exp = now + expireMillis; // 만료시'각'

        // 토큰의 payload에 claim들을 담아줍니다
        JwtBuilder builder = Jwts.builder()
                .claim("sub", subject) // 유저식별 subject = "doggabyang@gmail.com"
                .claim("iat", now/1000) // issuedAt 발급시각(현재시간 now)
                .claim("exp", exp/1000); // expireAt 만료시각( expireMillis )

        // 만약 builder부분에 (key, value)쌍을 더 추가 하고 싶다면,
        if (extraClaims != null){
            // map을 순회하면서, builder.claim(key, value)를 실행
            extraClaims.forEach((k,v)-> builder.claim(k, v));
        }

        return builder
                .signWith(key)
                // payload내부의 내용과
                // secret을 기반으로
                // base64인코딩을 통해서 암호를 만든다.
                .compact();
    }

    /*
    buildToken(
            String subject, // 유저 식별
            long expireMillis, // 만료시간
            Map<String, Object> extraClaims, // payload에 담을 데이터
            String type // access, refresh용도
    )
    */
    // 바로 위에 있는 토큰발급 코드메서드를 활용한다
    // accessToken발급
    public String generateAccessToken(String subject, Map<String, Object> claims){
        return buildToken(subject, accessExpireMillis, claims);
    }


    // 토큰 검증
    public Claims getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(key) // 복호화
                .build()
                .parseSignedClaims(token) // 여기서 실제 검증이 이루어짐
                // 1. signature 검증 (위조여부 검사) -> 검증실패시 예외 던지기
                // 2. 만료시각 검증 -> 검증실패시 예외
                .getPayload();
    }

    // Jwt토큰(문자열)을 사용자에게 발급한다
    // 사용자는 그 이후로부터 요청할때, 헤더에
    // Authorization : "Bearer " + 토큰문자열
    // 첨부하여 요청을 서버로 보내야 한다.
    public boolean isBearer(String header){
        // "Bearer " 접두를 떼준다.
        return header != null && header.startsWith("Bearer ");
    }

    public String removeBearer(String header){
        // "Bearer "접두를 떼준다
        return header.substring("Bearer ".length());
    }

}

