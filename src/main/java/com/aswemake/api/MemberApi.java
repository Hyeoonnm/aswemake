package com.aswemake.api;

import com.aswemake.dao.MemberDAO;
import com.aswemake.dto.MemberDTO;
import com.aswemake.dto.TokenDTO;
import com.aswemake.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/api")
@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;
    private final MemberDAO memberDAO;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO dto) throws Exception {
        Long signup = memberService.signup(dto);
        if (signup == null) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }else return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody MemberDTO member) {
        String login = memberService.login(member);
        TokenDTO token = new TokenDTO();
        if (login == null) {
            token.setToken("토큰 없음");
            return ResponseEntity.badRequest().body(token);
        }
        token.setToken(login);
        return ResponseEntity.ok(token);
    }
}

