package com.class302.omzteam.service;

import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto member = loginDao.LoginId(Long.valueOf(username));

        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }


        return User.builder()
                .username(String.valueOf(member.getMem_no()))
                .password(member.getMem_pw()) // 암호화된 비밀번호를 그대로 사용
                .roles("USER")
                .build();
    }
}