package com.example.demo.config;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
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
    public  JwtTokenProvider(@Value("${jwt.secret}")) String secretKey, @Value("${jwt.expiration}") int getExpiration {
        this.secretKey = secretKey;
    this. expiration = expiration;
    this.SECRET_KEY = new SecretKeySpec((java.util.Base64.getDecoder(secretKey), SignatureAlgorithm.HS12.getJcaName()));
    }
}// end of JwtTokenProvider
