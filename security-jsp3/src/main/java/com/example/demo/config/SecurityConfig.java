package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity //시큐어 어노테이션 활성화
public class SecurityConfig {
    /*
        로우 하이어라키란?
     - 상위 권한이 하위 권항르 자동으로 포함하돌고 만드는 권한 구조를 말한다...
       즉, 관리자 권한이면서, 사용자 권한도 당연히 가지게 된다. 
     1) 로그인 성공 -> 2) 사용자 권한 반환(UserDetails, getAuthorities())
     3) Spring security가 RoleHieracy를 적용
     4) 상위권한 -> 하위권한 자동 확장
     5) 인가 (HashRole, hashAuthority() 판단 시 반영
        최종 효과<br>
     - /user/** : USER 이상(= USER, MANAGER, ADMIN)
     - /manager/** : MANAGER 이상(= MANAGER, ADMIN)
     - /admin/** : ADMIN만
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl rh = new RoleHierarchyImpl();
        rh.setHierarchy("""
        ROLE_ADMIN > ROLE_MANAGER 
        ROLE_MANAGER > ROLE_USER
    """);
        return rh;
    }

    @Bean
    GrantedAuthoritiesMapper authoritiesMapper(RoleHierarchy roleHierarchy) {
        // 로그인 시 ADMIN이면 MANAGER/USER 권한을 자동으로 '추가' 부여
        return new RoleHierarchyAuthoritiesMapper(roleHierarchy);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/joinForm","/join").permitAll()// 이 패턴의 경우 권한 없이 접근이 가능하도록 한다.
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/loginForm") //사용자 정의한 로그인 페이지
                        // -> /login이 호출되면 시큐리티가 낚아채서 대신 로그인을 진행함
                        .loginProcessingUrl("/loginProcess") //로그인 요청 처리 URL(디폴트)
                        .defaultSuccessUrl("/") //로그인 성공 후 이동할 페이지
                        .failureUrl("/login-error")//로그인실패시 이동할 페이지
                        .permitAll()
                )
                //로그아웃 설정 추가
                .logout(logout -> logout
                    .logoutUrl("/logout")//로그아웃 처리 URL(디폴트)
                        .logoutSuccessUrl("/") //로그아웃 후 이동할 URL
                        .invalidateHttpSession(true) //세션 무효화(기본값 true)
                        .deleteCookies("JSESSIONID")//쿠키 삭제
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"));
        return http.build();
    }// end of defaultSecurityFilterChain
}