package com.example.demo.config;


import com.example.demo.dto.MemberLoginDto;
import com.example.demo.model.JwtAuthenticationResponse;
import com.example.demo.model.MemberVO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.AccessToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Log4j2 // sout 대신 로그 기록 확인용(디버그에 유용)
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MemberService memberService;
    private final  JwtTokenProvider jwtTokenProvider;
    public JwtAuthenticationResponse signin(MemberLoginDto memDto){
        MemberVO rmemVO = memberService.getMemberEmail(memDto);
        String email = rmemVO.getEmail();
        String role = rmemVO.getRole();
        String jwt = jwtTokenProvider.createToken(email,role);
        String refreshToken = jwtTokenProvider.createRefreshToken(email,role);
        log.info(refreshToken);
        JwtAuthenticationResponse jaResponse = new JwtAuthenticationResponse();
        jaResponse.setRefreshToken(refreshToken);
        jaResponse.setAccessToken(accessToken);
        jaResponse.setRole(role);
        jaResponse.setEmail(email);
        return jaResponse;
    }// end of signin
    public JwtAuthenticationResponse refreshToken(String token){
        String email = jwtTokenProvider.extractEmail(token);
        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setEmail(email);
        MemberVO rmemVO = memberService.getMemberEmail(memberLoginDto);
    }
}
