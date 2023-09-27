package com.class302.omzteam.member.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
public class MemberDto {
    private Long mem_no;
    private String mem_pw;
    private String mem_name;
    private int job;
    private String birth;
    private String email;
    private String phone;
    private Long dept_no;
    private LocalDate hiredate;

}
