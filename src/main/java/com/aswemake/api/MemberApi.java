package com.aswemake.api;

import com.aswemake.dto.MemberDTO;
import com.aswemake.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member/api")
@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;
    private final PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO dto) {
        MemberDTO existingMember = memberService.findByName(dto.getName());
        if (existingMember == null) {
            MemberDTO member = MemberDTO.builder()
                    .name(dto.getName())
                    .password(encoder.encode(dto.getPassword()))
                    .role(dto.getRole())
                    .build();
            memberService.signup(member);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Member exists");
    }
}

