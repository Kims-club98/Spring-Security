package com.example.demo.model;

import lombok.Data;

@Data// getter-setter를 한번에 처리 가능
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private String role;
    private String username;
    private String email;
}
