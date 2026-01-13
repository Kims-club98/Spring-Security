package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

// 구글 토큰 엔트포인트에서 내려주는 JSON을 그대로 매핑해서
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 없는 필드는 자동으로 무시하는 라이브러리
public class AccessTokenVO {
    //1. 사용자 프로필을 조회하는 API 호출
    private  String access_Token; // 변수선언: access_Token (API 호출용 토큰)
    private String expire_in; // 변수선언: expire_in (토큰 유효시간[초단위])
    private String scope; // 변수선언: scope (토큰으로 접근 가능한 범위, openid email profile)
    private  String token_type; // 변수선언: token_type (토큰의 타입[Bearer-default])
    private String id_token; // 변수선언: id_token(사용자가 누구인지 식별하는 JMT)
}// end of AccessTokenVO
