package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN. ROLE_MANAGER
    private String creatDate;
    // 소셜 로그인 - KAKAKO (개인공부는 NAVER...)
    private String provider; // google. kakako. Naver
    private String providerId;  // uid, 카카오식별자, 네이버 식별자
}
