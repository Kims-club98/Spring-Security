package com.example.demo.config;


import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
/*
* JwtTokenFilter의 역할: 클라이언트가 요청 시 토큰을 달고 다닌다
*   => 이 토큰이 정상적인지 서버 측에서 검증하는 과정이 필요하며 이를 이곳에서 처리함
* == 토큰을 검증하는 Code를 이곳에 작성해야 함
* */


@Component
public class JwtTokenFilter extends GenericFilter {
    @Value("${jwt.secret}")
    private String secretkey
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    }// end of doFilter
}// end of JwtTokenFilter
