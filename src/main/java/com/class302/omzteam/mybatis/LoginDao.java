package com.class302.omzteam.mybatis;

import com.class302.omzteam.member.model.MemberDto;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {

    public void register(@Param("memberDto") MemberDto memberDto) throws Exception;

    MemberDto getLoginId(@Param("mem_no") Long mem_no);

    Long getNextNum();

    String getPw();

    void updatePassword(@Param("mem_no") Long mem_no,
                        @Param("mem_pw") String mem_pw,
                        @Param("is_initial_login") boolean isInitialLogin);



//    void save(MemberDto memberDto);
}
