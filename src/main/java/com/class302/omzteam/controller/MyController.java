package com.class302.omzteam.controller;

import com.class302.omzteam.mybatis.MybatisDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class MyController {

    @Autowired
    MybatisDao mybatisDao;

    @GetMapping("/test")
    public void test() {
        log.info("------------ count {}", mybatisDao.selectCount());
    }
}
