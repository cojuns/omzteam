package com.class302.omzteam.mybatis;

import com.class302.omzteam.member.model.MemberDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;

@Mapper
public interface MemberDao {
    @Select("select count(*) from member")
    Integer memCnt();

    @Select("select * from member where mem_no = #{no}")
    MemberDto selectOne(Long no);

    @Insert("INSERT INTO daily_workstatus " +
            "(member_no,check_in,Date,is_late) " +
            "VALUES " +
            "(#{no}, DATE_FORMAT(NOW(), '%H:%i:%s'), DATE_FORMAT(NOW(), '%Y-%m-%d'), #{isLate})")
    int insertCheck(Long no, Long isLate);

    @Select("select check_in from daily_workstatus " +
            "where member_no = #{no} and date = DATE_FORMAT(NOW(), '%Y-%m-%d')")
    Time selectByTime(Long no);
}
