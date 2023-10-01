package com.class302.omzteam.config;



import com.class302.omzteam.service.MyUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptConfig bCryptConfig;

    @Autowired
    private MyUserDetailsService userDetailsService; // 사용자 정보를 제공하는 서비스

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/loginForm","/login/joinForm").permitAll() // 해당 페이지는 모두 접근 가능
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/loginForm")
                .usernameParameter("username") // 사용자 이름 파라미터명 설정
                .passwordParameter("password") // 비밀번호 파라미터명 설정
                .defaultSuccessUrl("/") // 로그인 성공 후 이동할 페이지
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .csrf().disable();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService) // 사용자 정보를 제공하는 서비스 설정
                .passwordEncoder(bCryptConfig.passwordEncoder()); // 사용자 비밀번호 암호화 설정
    }







}