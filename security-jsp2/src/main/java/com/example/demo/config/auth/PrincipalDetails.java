package com.example.demo.config.auth;

import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
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
    // *** 점검해야 할 Point
    // DB 설계 시 role 문자 형식
    // Spring Security에서 .hasRole('ADMIN'), .hasRole("USER")은 내부적으로 "ROLE_ADMIN", "ROLE_USER"
    // 勸張: DB "ROLE_ADMIN" 형태로 저장하거나, getAuthority()에서 "ROLE"을 붙여서 반환한다.
    // Return "ROLE_"+"user.getRole()
    // 시큐리티 성공 시 이 사용자가 어떤 권한을 가졌는지 알아야 함
    // 로그인 성공 시 토큰 內 권한목록을 채울 때 사용된다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole(); // ROLE_ADMIN, ROLE_USER, ROLE_MANAGER
            }
        });
        return collect;
    }// end of getAuthorities - 사용자가 어떤 방의 등급을 가지고 있는가?(권한여부 확인 메서드)

    @Override
    public String getPassword() {
        return user.getPassword();
    }// end of getPassword

    @Override
    public String getUsername() {
        return user.getUsername();
    }// end of getUsername

    public  String getEmail(){
            return user.getEmail();
    }// end of getEmail


    // 계정 관련 상태 체크용 매서드
    // true: 만료 아님(로그인 가능)
    // false: 계정 만료(로그인 불가)
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    // true: 잠김 아님(로그인 불가)
    // false: 잠김(로그인 불가) - 로그인 실패 5회, 관리자 잠금
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    // true: 비번 만료 아님
    // false: 비번 만료(로그인 불가) => 90마다 변경 진행
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    // 계정 활성화/비활성화 여부(휴먼계정)
    // true: 사용 가능(로그인 불가)
    // false: 사용 불가능(비활성화) => 휴면계정, 이메일 미인증의 경우...
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
