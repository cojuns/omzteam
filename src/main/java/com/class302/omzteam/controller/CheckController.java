package com.class302.omzteam.controller;

import com.class302.omzteam.mybatis.MemberDao;
import com.class302.omzteam.mybatis.MybatisDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/check")
@Log4j2
public class CheckController {

    @Autowired
    MemberDao memberDao;

    @Autowired
    MybatisDao mybatisDao;

    @GetMapping
    public String check() {
        return "check/checkForm";
    }

    @PostMapping("/in")
    public ResponseEntity<Map<String, Object>> checkIn(@RequestBody Map<String, Long> requestBody, Model model, RedirectAttributes rattr) {
        Long no = requestBody.get("no");
        Long isLate = requestBody.get("isLate");

        int rowCnt = memberDao.insertCheck(no, isLate);
        if (rowCnt == 1) {
            Time getTime = memberDao.selectByTime(no);
            model.addAttribute("time", getTime);

            // 응답 데이터를 JSON 형식으로 생성
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("time", getTime.toString()); // 시간 데이터를 문자열로 변환해서 전달

            return ResponseEntity.ok(responseData);
        } else {
            rattr.addFlashAttribute("msg", "CHK_ERR");
            return ResponseEntity.badRequest().build();
        }
    }
}
