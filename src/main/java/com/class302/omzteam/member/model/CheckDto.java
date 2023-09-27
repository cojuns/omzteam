package com.class302.omzteam.member.model;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CheckDto {

    private Long member_no;
    private Time check_in;
    private Time check_out;
    private Time work_hours;
    private Date date;
    private Long isLate;
    private Time overtime;

    public CheckDto(Long member_no, Time check_in, Date date, Long isLate) {
        this.member_no = member_no;
        this.check_in = check_in;
        this.date = date;
        this.isLate = isLate;
    }
}
