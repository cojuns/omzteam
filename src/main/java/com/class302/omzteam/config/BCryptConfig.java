package com.class302.omzteam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptConfig {

    @Bean // 암호화 설정 해당 처럼 @Configuration 설정해야 회원가입, 로그인 둘 다 암호화 적용 가능
          // SecurityConfig에 @Bean 설정 시 에러
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
