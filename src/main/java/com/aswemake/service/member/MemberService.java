package com.aswemake.service.member;

import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;

public interface MemberService {

    MemberDTO signup(MemberDTO member) throws Exception;
    MemberEntity getLoginUserByLoginId(String loginId);
}
