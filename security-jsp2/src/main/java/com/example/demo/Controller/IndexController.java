package com.example.demo.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스포츠 센터
// 역할(ROLE - 인가) : user, manager ,admin

@Log4j2
//@Controller - 각 메서드가 view를 리턴한다.
@Controller //@RestController
public class IndexController {
    @GetMapping({"","/"})
    public String home(){
        log.info("index");
        // -> /WEB-INF/views/((index)).jsp > Spring이 내부에서 수행함(개발자가 수행 X)
        // 어노테이션이 RestController에서 Controller로 변경됨
        // @RestController = @Controller + @ResponseBody -> 문자열 포멧
        // @Controller => 문자열이 출력으로 나갈 화면이다.
        return "index"; //  ViewResolver(Spring boot)
    }//end of home
    @GetMapping("/user")
    public String user(){
        log.info("home");
        return "home";
    }//end of home
    @GetMapping("/manager")
    public String manager(){
        log.info("manager");
        return "manager";
    }//end of home
    @GetMapping("/admin")
    public String admin(){
        log.info("admin");
        return "admin";
    }//end of home
    @GetMapping("/joinForm")
    public String joinForm() {
        log.info("joinForm");
        //auth/joinForm -> 응답페이지 확면 이름임
        // yaml -> /WEB-INF/views/ 접두어
        // 접미어  -> .jsp
        return "auth/joinForm";
    }

    @GetMapping("/login-error")
    public String loginError(){
        log.info("login-error");
        return "loginError";
    }

}// end of IndexController