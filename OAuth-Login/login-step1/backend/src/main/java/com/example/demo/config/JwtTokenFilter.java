package com.example.demo.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
* JwtTokenFilter의 역할: 클라이언트가 요청 시 토큰을 달고 다닌다
*   => 이 토큰이 정상적인지 서버 측에서 검증하는 과정이 필요하며 이를 이곳에서 처리함
* == 토큰을 검증하는 Code를 이곳에 작성해야 함
* */


@Component
public class JwtTokenFilter extends GenericFilter {
    @Value("${jwt.secret}")
    private String secretKey;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String token = httpRequest.getHeader("Authorization");
        try{
            if (token != null) {
                if(!token.substring(0, 7).equals("Bearer ")) {
                    throw new AuthenticationServiceException("Bearer 형식이 아닙니다.");
                }
                // 검증 시 Bearer를 떼어내고 검증
                String jwtToken = token.substring(7);
                /* - 이 토큰을 가지고 검증하고 여기서 claims는 payload를 가리키는데
                   - 이것을 꺼내서 Authenticationㅇ */
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtToken)
                        .getBody();//검증을 하고 Claims를 꺼내는 메서드임
                List<GrantedAuthority> authorities = new ArrayList<>();//권한이 여러가지 일 수 있으므로 List에 담아줌.
                authorities.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role")));
                // clamㄴ.getSubject()에 Email 정보도 확인 가능
                UserDetails userDetails = new User(claims.getSubject(), "", authorities);
                // Spring에서 Authentication 객체가 있으면 로그인을 했다라고 판단
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
                // 인증정보는 SecurityContextHolder안에 securityContext안에 들어있다.
                //log.info(SecurityContextHolder.getContext().getAuthentication().getName());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // ▼ 아래 코드가 없으면 다음 필터로의 연결이 되지 않음(필터를 갔다가 다시 FilterChain으로 돌아가게 하는 코드)
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());//401응답줌
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("invalid token");
        }

    } // end of doFilter
}// end of JwtTokenFilter
