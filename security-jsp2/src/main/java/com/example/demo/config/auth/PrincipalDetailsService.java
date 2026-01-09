package com.example.demo.config.auth;

import com.example.demo.dao.MemberDao;
import com.example.demo.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//이 클래스는 언제 동작되냐면
// 시큐리티 설정에서 loginProcessingUrl("/login")
// -> /login에 대한 요청이 오면 자동으로 UserDetailService타입으로 Ioc되어있는 loadUserByUsername이 실행된다.
// 시큐리티 session내부에 Authentication내부에 UserDetails가 들어감...
@Log4j2
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private  final  MemberDao memberDao;

    public PrincipalDetailsService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인 요청이 있을 때 불러옴
        log.info("loadUserByUsername username=()",username);
        // User타입은 Authentication에 직접 담을 수 없다...
        // Authentication에 담을 수 있는 타입은 오직 UserDetail타입만 가능함.
        User user = memberDao.login(username);
        log.info(user.toString());//오라클 서버에서 가져온 값
        return null;
    }
}
