package com.class302.omzteam.controller;

import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import com.class302.omzteam.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    LoginDao loginDao;

    @Autowired
    PasswordEncoder passwordEncoder;






    @GetMapping("/login/joinForm")
    public String registerForm(Model model) {



        Long nextMno = loginDao.getNextNum();
        MemberDto memberDto = new MemberDto();
        memberDto.setMem_no(nextMno);



        model.addAttribute("memberDto", memberDto);
        return "login/joinForm";
    }



    @PostMapping("/login/joinForm")
    public String registerMember(@ModelAttribute MemberDto memberDto) throws Exception {

        String encryptedPassword = passwordEncoder.encode(memberDto.getMem_pw());
        memberDto.setMem_pw(encryptedPassword);


        loginDao.register(memberDto);


        return "redirect:/";
    }



    @GetMapping("/login/loginForm")
    public String loginForm() {



        System.out.println("member =======> ");

        return "login/loginForm";
    }


    @GetMapping("/main")
    public String main(Model model, HttpSession session, HttpServletRequest request) {
        // 현재 로그인한 사용자의 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 이름 또는 기타 정보 가져오기
        String username = authentication.getName();


        Boolean showChangePasswordPopup = (Boolean) session.getAttribute("showChangePasswordPopup");


        if (showChangePasswordPopup == null || !showChangePasswordPopup) {
            showChangePasswordPopup = false;
        }

        model.addAttribute("showChangePasswordPopup", showChangePasswordPopup);
        model.addAttribute("username", username);

        return "main";
    }




    @GetMapping("/login/changePassword")
    public String showChangePasswordForm(Model model, HttpSession session) {
        // 세션에서 showChangePasswordPopup 값을 가져와서 JavaScript 변수에 할당
        boolean showChangePasswordPopup = session.getAttribute("showChangePasswordPopup") != null && (boolean) session.getAttribute("showChangePasswordPopup");



        model.addAttribute("showChangePasswordPopup", showChangePasswordPopup);
        return "login/changePassword";
    }





    @PostMapping("/login/changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam String newPassword,
                                              @RequestParam String confirmPassword,
                                              Authentication authentication, HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        if (authentication == null || !authentication.isAuthenticated()) {
            response.put("success", false);
            response.put("errorMessage", "로그인되어 있지 않습니다.");
            return response;
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        if (!newPassword.equals(confirmPassword)) {
            response.put("success", false);
            response.put("errorMessage", "비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return response;
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);
        MemberDto memberToUpdate = new MemberDto();
        memberToUpdate.setMem_no(Long.parseLong(username));
        memberToUpdate.setMem_pw(encryptedPassword);
        memberToUpdate.set_initial_login(false);

        loginDao.updatePassword(memberToUpdate.getMem_no(),
                memberToUpdate.getMem_pw(),
                memberToUpdate.is_initial_login());




        session.setAttribute("showChangePasswordPopup", false);

        response.put("success", true);
        response.put("redirectUrl", "/main");  // 클라이언트에게 리다이렉트할 URL 전송

        return response;
    }












}
