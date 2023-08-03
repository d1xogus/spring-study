package com.example.maple.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
// GenericFilterBean을 상속하여 커스텀 필터를 생성. Spring Security 필터 체인에서 동작
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    // 필터가 실제로 처리되는 로직을 구현. JWT 토큰을 가져오고, 유효성 검사를 수행한 후, 인증 정보를 SecurityContextHolder에 설정
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException{
        String token = resolveToken((HttpServletRequest) servletRequest);     //요청에서 JWT 토큰을 가져옴
        if(token != null && jwtTokenProvider.validateToken(token)){     //가져온 JWT 토큰의 유효성을 JwtTokenProvider의 validateToken() 메서드를 사용하여 검사
            Authentication authentication = jwtTokenProvider.getAuthentication(token);      //유효한 JWT 토큰을 기반으로 사용자의 인증 정보를 가져옵니다. JwtTokenProvider의 getAuthentication() 메서드를 사용
            SecurityContextHolder.getContext().setAuthentication(authentication);   //검증된 사용자의 인증 정보를 SecurityContextHolder에 설정
        }
        chain.doFilter(servletRequest, servletResponse);      //필터 체인의 다음 필터를 호출하여 요청을 처리
    }

    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("X-AUTH-TOKEN");
    }
//    private String resolveToken(HttpServletRequest request){
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
}
