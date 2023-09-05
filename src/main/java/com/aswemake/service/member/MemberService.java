package com.aswemake.service.member;

import com.aswemake.dto.MemberDTO;
import com.aswemake.entity.MemberEntity;

import java.util.Map;

public interface MemberService {

    Long signup(MemberDTO member) throws Exception;
    MemberEntity getLoginUserByLoginId(String loginId);
    MemberEntity getLoginUserById(Long memberId);
}
