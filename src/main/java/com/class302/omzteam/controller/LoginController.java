package com.class302.omzteam.controller;

import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import com.class302.omzteam.service.CustomUserDetails;
import com.class302.omzteam.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginDao loginDao;

    @Autowired
    PasswordEncoder passwordEncoder;






    @GetMapping("/joinForm")
    public String registerForm(Model model) {



        Long nextMno = loginDao.getNextNum();
        MemberDto memberDto = new MemberDto();
        memberDto.setMem_no(nextMno);

//        String getPw = loginDao.getPw();
//        memberDto.setMem_pw(getPw);


        model.addAttribute("memberDto", memberDto);
        return "login/joinForm";
    }



    @PostMapping("/joinForm")
    public String registerMember(@ModelAttribute MemberDto memberDto) throws Exception {

        String encryptedPassword = passwordEncoder.encode(memberDto.getMem_pw());
        memberDto.setMem_pw(encryptedPassword);


        loginDao.register(memberDto);


        return "redirect:/";
    }



    @GetMapping("/loginForm")
    public String loginForm() {
        System.out.println("member =======> ");

        return "login/loginForm";
    }






//    @PostMapping("/loginForm")
//    public String loginProcess(@RequestParam Long mem_no, @RequestParam String mem_pw) {
//        MemberDto member = loginDao.getLoginId(mem_no);
//        System.out.println("member =======> "+member);
//        if (member != null) {
//            // 비밀번호 매칭 확인
//            if (passwordEncoder.matches(mem_pw, member.getMem_pw())) {
//                // Spring Security로 사용자 인증을 진행
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                // 로그인 성공 후 리다이렉트
//                return "/";
//            }
//        }
//        // 로그인 실패 시 다시 로그인 페이지로 이동
//        return "redirect:/login/joinForm";
//    }

    @GetMapping("/changePassword")
    public String showChangePasswordForm() {
        return "login/changePassword";
    }



    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model,
                                 Authentication authentication) {

        // 1. 현재 로그인된 사용자 확인
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("errorMessage", "로그인되어 있지 않습니다.");
            return "redirect:/login/loginForm";
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // 2. 새로운 비밀번호와 확인 비밀번호가 동일한지 검사
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return "passwordChangeForm";  // 비밀번호 변경 폼으로 되돌아갑니다.
        }



        // 4. 사용자의 비밀번호 업데이트
        String encryptedPassword = passwordEncoder.encode(newPassword);
        MemberDto memberToUpdate = new MemberDto();
        memberToUpdate.setMem_no(Long.parseLong(username));
        memberToUpdate.setMem_pw(encryptedPassword);
        memberToUpdate.set_initial_login(false); // 초기 로그인 상태를 false로 변경

        loginDao.updatePassword(memberToUpdate.getMem_no(),
                memberToUpdate.getMem_pw(),
                memberToUpdate.is_initial_login());


        return "redirect:/";  // 홈 페이지 또는 원하는 페이지로 리다이렉트
    }











}
