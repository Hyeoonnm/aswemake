package com.aswemake.service.member;

import com.aswemake.dto.MemberDTO;

public interface MemberService {
    MemberDTO findByName(String name);

    MemberDTO signup(MemberDTO member);
}
