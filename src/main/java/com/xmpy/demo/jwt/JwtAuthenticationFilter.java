package com.xmpy.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


// filter -> 요청과 응답의 전처리 or 후처리를 위한 존재
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 상속받은거임..
    // 3번째

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 매개변수 3가지
        // request = 요청 -- filter을 거쳐서 servlet으로 들어온다.
        // filterChain = 세세하세 나누어져잇다 - (다음다음필터로 넘겨준다)
        // response = 응답 - Controller에서 responseBody로 응답을 한다 - 그거를 response라는 객체로 가지고 있는다

        // 1. 예비요청 패스 - Cors 에러 관련
        // 예비요청은 get, post ... 실제 요청 전에 항상 브라우저가 보내는 요청
        // 대충 예비로 요청을 보내는 것 - 정찰병 보내기
        String requestMethod = request.getMethod();
        if (requestMethod.equalsIgnoreCase("OPTIONS")){
            filterChain.doFilter(request, response); // 다음 필터로 패스
            return;
        }

        // 요청에 담긴 jwt토큰 추출
        // - 요청의 헤더에 존재 (authorization key에 담아둠)
        String authHeader = request.getHeader("authorization");

        // Bearer 없으면 → 그냥 통과
        if (!jwtUtil.isBearer(authHeader)){
            filterChain.doFilter(request, response);
            return;  // ← 여기서 끝!
        }

        // Bearer 있으면 → 토큰 검증
        String token = jwtUtil.removeBearer(authHeader);

        try {
            Claims claims = jwtUtil.getClaims(token);
            String email = claims.get("sub", String.class);

            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            JwtAuthentication authentication = new JwtAuthentication(email, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        catch (JwtException e) {
            System.out.println("토큰 검증 실패: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }


}