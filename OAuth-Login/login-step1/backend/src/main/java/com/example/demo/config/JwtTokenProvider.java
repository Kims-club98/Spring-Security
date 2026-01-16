package com.example.demo.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
/*
 * JWT토큰을 발급하는 스프링 컴포넌트(싱글톤)
 *   -> 이메일과 권한(ROLE) 정보를 안전하게 암호화 하여 Token으로 반환해줌!
 *   -> 이 토큰으로 사용자의 인증/인가를 처리(토큰은 만료 시간이 있어, 보안성을 높이고 세션리스서비스 구현에 적합함)
 * */



@Component
public class JwtTokenProvider {
    // 인코딩 값은 시크릿 값임...
    private final String secretKey;
    private final int expiration;
    // cf) private: 클래스 내부에서만 사용할 것(외부 클래스 접근 차단), final: 변수 값이 변하지 않음(수정 불가)
    private Key SECRET_KEY;
    public  JwtTokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") int getExpiration) {
        this.secretKey = secretKey;
    this. expiration = expiration;
    this.SECRET_KEY = new SecretKeySpec((Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS512.getJcaName());;
    }// end ofJwtTokenProvider
    /*  - Claims 생성
            -> JWT의 payload부분(=실제 데이터)에 들어갈 내용
            -> setSubject(email0: 이 토큰 주인은 email(주체내용)
            -> claim.put("role", role): 사용자권한(로우 하이어라키)
            -> 토큰을 생성
                -> SetIssueAt(now): 토큰 발급 시간
                -> setExpiration(): 토큰 만료 시간(현재시간 + 유효시간)
                -> signWith(SECRET_KEY):앞서 만든 Key로 Hs512해시 알고리즘으로 서명
            -> 토큰 반환
                - .compact(): JMT문자로 직렬화하여 반환
              이 토큰을 프론트엔트(리엑트)에게 응답하면, 프론트는 이 토큰(access Token)을 저장하고 API를 요청 할때 마다
              Authorization헤더에 넣어서 인증한다.
     */
    public String createToken(String email, String role){
        // Claim는 jwt토큰의 payload부분을 의미
        // 각종 사용자 정보를 payload해서 넣을 수 있음
        Claims claims = Jwts.claims().setSubject(email);//주된 정보는 이메일로
        claims.put("role",role);
        Date now = new Date() {
        };
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 발행시간
                .setExpiration(new Date(now.getTime() + expiration*60*1000L))
                .signWith(SECRET_KEY)
                .compact();
        return token;
    }// end of createToken
}// end of JwtTokenProvider
