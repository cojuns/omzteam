package com.class302.omzteam;

import com.class302.omzteam.member.model.MemberDto;
import com.class302.omzteam.mybatis.LoginDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OmzteamApplicationTests {

    @Autowired
    LoginDao loginDao;

    @Test
    void contextLoads() {
    }

    @Test
    void getNextNum(){

    String Next = loginDao.getPw();

        System.out.println(Next);

    }

}
