package com.example.demo.controller;

import com.example.demo.dto.RedirectDto;
import com.example.demo.model.AccessTokenVO;
import com.example.demo.service.GoogleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/member")
public class MemberController {
    // GoogleService 의존성 주입
    private final GoogleService googleService; // ♣ 주의: 000=null;로 초기화 하지 말 것(NPE 뜸)
    // http://localhost:8000/member/google/doLogin
    @PostMapping("/google/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody RedirectDto redirectDto){
        log.info("googleLogih");
        /*  1. 프론트에서 넘어온 인가 코드(code)를 받는 API
            React가 구글 로그인 성공 후, 구글이 준 code를 Backend로 전달함
            이 code는 'Access Token 발급'을 위한 1회성 교환권 같은 값이다! */
        log.info("redirectDto:{}",redirectDto.getCode());
        // 2.인가 코드로 구글 Access Token을 발급 받기
        AccessTokenVO accessTokenVO = googleService.getAccessToken(redirectDto.getCode());
        // access token은 구글 API를 호출할 수 있는 열쇠
        // 3. Access Token를 활용해서 구글 API를 호출할 수 있는 열쇠
        // 4. 회원가입이 되어 있는지 여부를 파악해서 강제로 회원가입을 시킨다
        // 5. 우리 서비스에서 JWT 토큰을 발급하기
        // 6. Front로 내려줄 Login 결과를 구성해줘야 함.
        Map<String, Object> logininfo = new HashMap<>();
        return new ResponseEntity<>(redirectDto, HttpStatus.OK);
    }// end of googleLogin
    @PostMapping("/kakao/doLogin")
    // http://localhost:8000/member/kakao/doLogin
    public ResponseEntity<?> kakaoLogin(@RequestBody RedirectDto redirectDto){
        log.info("kakaoLogin");
        log.info("redirectDto:{}",redirectDto.getCode());
        return new ResponseEntity<>(redirectDto, HttpStatus.OK);
    }// end of kakaoLogin
}
