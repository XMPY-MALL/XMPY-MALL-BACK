package com.xmpy.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    // 외부라이브러리
    @Bean  // 사용자 비밀번호를 암호화하는 객체 (시큐리티 라이브러리)
    public BCryptPasswordEncoder  bCryptPasswordEncoder() {
        // db 조회를 했을때, 실제password가 아니라, 123!@$~~ 같은 암호롤 저장
        return new BCryptPasswordEncoder();
    }

    /*
    Cors설정 - CORS 에러를 방지하기 위해 설정
    브라우저에서 다른출처(origin)로 요청을 보낼때
    브라우저정책위반 때문에 요청을 막아서 발생하는 에러

    -> origin이 다르면, 에러가 발생
    *origin) 프로토콜(http, https), 도메인(localhost, naver.com) 포트(3000, 8080)
    프로토콜 + 도메인 + 포트 모두 일치해야 같은 origin

    전통웹(SSR)방식에서는 http:localhost:8080/page.html -> 사용자 화면
    http:localhost:8080/main.js 주세요(요청) 가능 (origin이 같기 때문에)
    http:localhost:8080/img.png 주세요(요청) 가능

    but, http:localhost:5173 에서 http:localhost:8080 으로 요청은 불가능 ( origin이 다르기 때문에 )
    -> 이런 상황을 '보안상 위험'이라고 판단 -> Cors 에러

    결론: 서버(localhost8080)에서 우리에게 보내는 요청은 안전합니다를 설정 해야함
         서로 다른 origin끼리도 데이터를 주고 받을 수 있는 것
    */


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cors = new CorsConfiguration();

        // 요청을 보내는 쪽의 도메인(naver.com) 모두 허용
        cors.addAllowedOriginPattern(CorsConfiguration.ALL);
        // 요청을 보내는 쪽의 Req, Res 헤더 정보에 대한 제한 모두 허용
        cors.addAllowedHeader(CorsConfiguration.ALL);
        // 요청 보내는 쪽의 메서드(get, post...) 모두 허용
        cors.addAllowedMethod(CorsConfiguration.ALL);

        // 요청 url에 대한 cors설정을 적용 하기 위한 객체 (배달부)
        UrlBasedCorsConfigurationSource sc =  new UrlBasedCorsConfigurationSource();

        // /** 모든 패턴에 대해서 모두 적용
        sc.registerCorsConfiguration("/**", cors);
        return sc;
    }


    // filterChain 설정 (filter 설정)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 위에서 Bean으로 만든 설저객체를 security에 적용
        http.cors(Customizer.withDefaults());
        // 세션기반 기능들 off
        // csrf : 세션기반 공격(사용자의 세션을 탈취해서 공격)
        http.csrf(csrf -> csrf.disable());
        // 폼 로그인 (서버사이드 렌더링 방식)
        http.formLogin(form -> form.disable());
        // 세션기반 로그인 방식
        http.httpBasic(basic -> basic.disable());
        // 세션 로그아웃
        http.logout(lg -> lg.disable());
        // 세션을 무상태 방식으로 변경 (jwt 토큰 방식)
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 세션기반 기능 다 OFF

        // JWT 관련 필터 설정 ( 나중에 )

        // url 요청에 대한 권한 설정
        http.authorizeHttpRequests(auth-> {
            // 특정요청에 대해서는 401에러 안내리고, 항상 내려주겠다..
            // auth.requestMatchers("/post/**", "/product/**").permitAll();
            // 그 외 모든 경로에 대해서는 검사하겠다.
            // auth.anyRequest().authenticated();

//
//            // 사장 권한 설정
//            auth.requestMatchers("/admin/**").hasRole("ADMIN");
            // 우선은, 모두 통과
            auth.anyRequest().permitAll();
        });
        return http.build();
    }
}


// 실행을 했을때 스프링 시큐리티에서 자동생성되는 패스워드가 있는상태
// 그말은 지금 유저네임 패스워드 방식으로 로그인이 적용이 안되고있다
// 시큐리티 오프해논 상태인데도 불구하고 비밀번호가 생성이 되고있다
// localhost8080/login 이 시큐리티적용되면 디폴트로 생성되어야하는데
// 그것도 접속이 안되고있다
// 펄밋올은 적용이
