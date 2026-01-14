package com.example.demo.service;

import com.example.demo.dto.GoogleProfileDto;
import com.example.demo.model.AccessTokenVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Log4j2
@Service
@RequiredArgsConstructor
public class GoogleService {
    @Value("${oauth.google.client_id}")
    private String client_id;
    @Value("${oauth.google.client-secret}")
    private String clientSecret;
    @Value("${oauth.google.redirect-uri}")
    private String redirectUri;
    // google에서 발급하는 accessToken 받기
    // google계정으로 로그인하면 인가코드를 보여줌
    //
    // RestClient로 처리함(spring 6)
    // 리엑트에서 axios가 하는 역할과 비슷하다.
    public AccessTokenVO getAccessToken(String code){
        log.info(code);
        // 서버(8000번 Spring)에서 서버(Google Server)로 요처을 할때에는 RestClient로 처리를 함(Spring 6)
        RestClient resClient = RestClient.create();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("clinet_id", client_id);
        params.add("client_secret", clientSecret);
        params.add("code",code); // 인가코드를 보내야 함!
        params.add("redirect_uri",redirectUri);
        params.add("grant_type", "authorization_code");
        ResponseEntity<String> res = resClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .header("Content-Type","application/x-ww-form-urlencoded")
                .body (params)
                .retrieve()
                .toEntity(String.class);
        log.info(res.getBody());
        return null;
    };// end of getAccessToken
     // 사용자 정보 얻기
    public GoogleProfileDto getGoogleProfile(String token){
        log.info("getGoogleProfile");
        log.info("token: {} ",token);
        RestClient restClient = RestClient.create();
        ResponseEntity<GoogleProfileDto> response = restClient.get()
                .uri("https://openidconnect.googleapis.com/v1/userinfo")
                .header("Authorization", "Bearer "+token)
                .retrieve()
                .toEntity(GoogleProfileDto.class);
        return  response.getBody();
    }// end of getGoogleProfile
}// end of GoogleService
