package com.class302.omzteam.controller;

import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginDao loginDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/joinForm")
    public String registerForm(Model model){
    model.addAttribute("memberDto", new MemberDto());
        return "login/joinForm";
    }

    @PostMapping("/joinForm")
    public String registerMember(MemberDto memberDto) throws Exception {
        String encryptedPassword = passwordEncoder.encode(memberDto.getMem_pw());
        memberDto.setMem_pw(encryptedPassword);

        loginDao.register(memberDto);

        return "redirect:/main";
    }

}
