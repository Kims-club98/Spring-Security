package com.example.demo.config;


import com.example.demo.dto.MemberLoginDto;
import lombok.extern.log4j.Log4j2;
import oracle.net.ano.AuthenticationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/singin")
    public ResponseEntity<String> signin(@RequestBody MemberLoginDto memDto){
        log.info("signin:"+memDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
