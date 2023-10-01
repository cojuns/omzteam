package com.class302.omzteam.controller;

import com.class302.omzteam.config.BCryptConfig;
import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginDao loginDao;

    @Autowired
    private BCryptConfig bCryptConfig;

    @Autowired
    private AuthenticationManager authenticationManager;



    @GetMapping("/joinForm")
    public String registerForm(Model model) {
        Long nextMno = loginDao.getNextNum();
        MemberDto memberDto = new MemberDto();
        memberDto.setMem_no(nextMno);
        model.addAttribute("memberDto", memberDto);
        return "login/joinForm";
    }



    @PostMapping("/joinForm")
    public String registerMember(MemberDto memberDto) throws Exception {
        String encryptedPassword = bCryptConfig.passwordEncoder().encode(memberDto.getMem_pw());
        memberDto.setMem_pw(encryptedPassword);

        loginDao.register(memberDto);

        return "redirect:/main";
    }

    @GetMapping("/loginForm")
    public String loginForm() {

        return "login/loginForm";
    }


    @PostMapping("/loginForm")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 사용자가 입력한 로그인 정보로 인증 객체 생성
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        try {
            // 인증 매니저를 사용하여 사용자 인증 시도
            Authentication authentication = authenticationManager.authenticate(token);

            // 인증 성공 시 SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("로그인성공");

            // 로그인 성공 후 리다이렉트할 페이지 설정
            return "redirect:/main";
        } catch (Exception e) {
            // 인증 실패 시 예외 처리
            System.out.println("로그인실패");

            return "redirect:/login/loginForm?error";
        }
    }
}
