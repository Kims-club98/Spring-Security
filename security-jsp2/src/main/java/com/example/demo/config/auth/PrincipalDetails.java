package com.example.demo.config.auth;

import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
/*
*   == 시큐리티가 /login주소요청이 오면 낚아채서 로그인을 진행함
*      로그인 진행이 완료되면 시큐리티가 가진 session이 만들어짐
*      같은 세션공간인데 시큐리티만의 공간이 있음.(Security ContextHolder에 키를저장)
*      오브젝트 -> Authentication타입 객체만 들어갈 수 있음.
*      User타입 -> UserDetail타입  객체만 들어갈 수 있다.
*      Security Session영역에 들어갈 수 잇는 객체는 Authentication만 들어갈 수 있으며
*      Authentication 안에서 UserDetails를 꺼낼 수 있다.(사용자 정보 Session에 있던 정보)
*      Authentication객체는 PrincipalDetailsService에서 만들어줌
* */


public class PrincipalDetails implements UserDetails {
    // 우리 서비스의 사용자 엔티티
    // DB에서 조회한 사용자 정보
    private User user;
    //생성자: 로그인시 DB에서 조회된 User를 받아서 PrincipalDetails로 감싼다.
    public PrincipalDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
