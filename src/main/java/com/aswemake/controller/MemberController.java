package com.aswemake.controller;

import com.aswemake.entity.MemberEntity;
import com.aswemake.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/info")
    public String userInfo(Model model, Authentication auth) {

        MemberEntity loginUser = memberService.getLoginUserByLoginId(auth.getName());

        if(loginUser == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("user", loginUser);
        return "member/info";
    }

    @GetMapping("/admin")
    public String adminPage() {

        return "member/admin";
    }
}
