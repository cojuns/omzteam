package com.class302.omzteam.mybatis;

import com.class302.omzteam.member.model.MemberDto;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {

    public void register(@Param("memberDto") MemberDto memberDto) throws Exception;

    MemberDto LoginId(@Param("mem_no") Long mem_no);

    Long getNextNum();
}
