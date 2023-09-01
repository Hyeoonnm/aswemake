package com.aswemake.api;

import com.aswemake.dto.MemberDTO;
import com.aswemake.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/v1/api")
@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        memberService.signup(memberDTO);
        return ResponseEntity.ok().body("회원가입 완료");
    }
}
